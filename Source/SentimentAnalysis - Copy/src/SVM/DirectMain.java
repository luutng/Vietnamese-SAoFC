package SVM;

import java.io.*;
import java.util.*;

import libsvm.*;

public class DirectMain {

    static svm_parameter _param;
    static svm_problem _prob;
    static String _model_file;
    static String _file_train;
    static String _file_test;
    static double fi = 0;

    public static void main(String[] args) throws IOException {
        //SVM.IndirectMain.scaling();
        //System.out.println();
        //_file_train = "src\\Data\\trainSen";
        //_file_test = "src\\Data\\testSen";
        //_file_train = "src\\Data\\trainSentiment.scale";
        //_file_test = "src\\Data\\testSentiment.scale";
        //libSVMdemo();
        //exResult();
        runDirect("Sub");
        runDirect("Sen");
    }

    private static double atof(String s) {
        double d = Double.valueOf(s).doubleValue();
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            System.err.print("NaN or Infinity in input\n");
            System.exit(1);
        }
        return (d);
    }

    private static int atoi(String s) {
        return Integer.parseInt(s);
    }

    private static void read_problem(String _file) throws IOException {
        BufferedReader fp = new BufferedReader(new FileReader(_file));
        Vector<Double> vy = new Vector<Double>();
        Vector<svm_node[]> vx = new Vector<svm_node[]>();
        int max_index = 0;

        while (true) {
            String line = fp.readLine();
            if (line == null) {
                break;
            }

            StringTokenizer st = new StringTokenizer(line, " \t\n\r\f:");

            vy.addElement(atof(st.nextToken()));
            int m = st.countTokens() / 2;
            svm_node[] x = new svm_node[m];
            for (int j = 0; j < m; j++) {
                x[j] = new svm_node();
                x[j].index = atoi(st.nextToken());
                x[j].value = atof(st.nextToken());
            }
            if (m > 0) {
                max_index = Math.max(max_index, x[m - 1].index);
            }
            vx.addElement(x);
        }

        _prob = new svm_problem();
        _prob.l = vy.size();
        _prob.x = new svm_node[_prob.l][];
        for (int i = 0; i < _prob.l; i++) {
            _prob.x[i] = vx.elementAt(i);
        }
        _prob.y = new double[_prob.l];
        for (int i = 0; i < _prob.l; i++) {
            _prob.y[i] = vy.elementAt(i);
        }

        if (_param.gamma == 0 && max_index > 0) {
            _param.gamma = 1.0 / max_index;
        }

        if (_param.kernel_type == svm_parameter.PRECOMPUTED) {
            for (int i = 0; i < _prob.l; i++) {
                if (_prob.x[i][0].index != 0) {
                    System.err.print("Wrong kernel matrix: first column must be 0:sample_serial_number\n");
                    System.exit(1);
                }
                if ((int) _prob.x[i][0].value <= 0 || (int) _prob.x[i][0].value > max_index) {
                    System.err.print("Wrong input format: sample_serial_number out of range\n");
                    System.exit(1);
                }
            }
        }
        fp.close();
    }

    protected static void training() throws IOException {
        read_problem(_file_train);
        System.out.print("Training...\n");
        _model_file = _file_train + ".model";

        try {
            svm_model model = svm.svm_train(_prob, _param);
            System.out.println("Done!!");
            svm.svm_save_model(_model_file, model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static double[] testing() throws IOException {
        read_problem(_file_test);
        System.out.print("\nTesting...\n");
        svm_model model;
        int correct = 0, total = 0;
        double arrRe[] = new double[_prob.l];
        try {
            model = svm.svm_load_model(_model_file);

            for (int i = 0; i < _prob.l; i++) {
                double v;
                svm_node[] x = _prob.x[i];
                v = svm.svm_predict(model, x);
                arrRe[i] = v;
                total++;
                if (v == _prob.y[i]) {
                    correct++;
                }
            }

            double accuracy = (double) correct / total * 100;
            System.out.println("Accuracy = " + accuracy + "% (" + correct + "/" + total + ")");
            fi = accuracy;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrRe;
    }

    public static void libSVMdemo() throws IOException {
        // default values
        _param = new svm_parameter();
        _param.svm_type = svm_parameter.C_SVC;
        _param.kernel_type = svm_parameter.RBF; //LINEAR
        _param.degree = 3;
        _param.gamma = Math.pow(2, 1);		// 1/num_features	// default : 0
        _param.coef0 = 0;
        _param.nu = 0.5;
        _param.cache_size = 100;
        _param.C = Math.pow(2, 1);			// default: 1
        _param.eps = 1e-3;
        _param.p = 0.1;
        _param.shrinking = 1;
        _param.probability = 0;
        _param.nr_weight = 0;
        _param.weight_label = new int[0];
        _param.weight = new double[0];
        training();
        testing();
    }

    public static double exResult(String type) throws IOException {
        double[] arrRe = testing();
        try {
            FileOutputStream fos = new FileOutputStream("src\\Data\\dirOUT" + type, false);
            try (PrintWriter pw = new PrintWriter(fos)) {
                for (int i = 0; i < arrRe.length; i++) {
                    pw.println(arrRe[i]);
                }
            }
        } catch (Exception e) {
        }
        return fi;
    }

    public static double runDirect(String type) throws IOException {
        SVM.IndirectMain.runningForTest(type);
        _file_train = "src\\Data\\train" + type;
        _file_test = "src\\Data\\test" + type;
        libSVMdemo();
        return exResult(type);
    }
}
