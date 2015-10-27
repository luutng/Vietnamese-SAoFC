package SVM;

import java.io.*;
import static SVM.svm_train.cva;

public class IndirectMain {

    static String _file_train;
    static String _file_test;
    static String _file_scale_test;
    static String _file_scale_train;
    static String _file_model;
    static String _file_scale_para;
    static String _file_output;

    public static void scaling() throws IOException {
//        "Usage: svm-scale [options] data_filename\n"
//		+"options:\n"
//		+"-l lower : x scaling lower limit (default -1)\n"
//		+"-u upper : x scaling upper limit (default +1)\n"
//		+"-y y_lower y_upper : y scaling limits (default: no y scaling)\n"
//		+"-s save_filename : save scaling parameters to save_filename\n"
//		+"-r restore_filename : restore scaling parameters from restore_filename\n"
//              +"-o output_filename : save scaling output to output_filname"
        String[] args = new String[]{"-l", "0", "-u", "1", "-s", _file_scale_para, "-o ", _file_scale_train, _file_train};
        svm_scale.main(args);
        //args = new String[]{"-l", "0", "-u", "1", "-r", _file_scale_para, "-o ", _file_scale_test, _file_test};
        //svm_scale.main(args);
    }

    static void training() throws IOException {
//         "Usage: svm_train [options] training_set_file [model_file]\n"
//		+"options:\n"
//		+"-s svm_type : set type of SVM (default 0)\n"
//		+"	0 -- C-SVC		(multi-class classification)\n"
//        
//		+"-t kernel_type : set type of kernel function (default 2)\n"
//		+"	0 -- linear: u'*v\n"
//		+"	1 -- polynomial: (gamma*u'*v + coef0)^degree\n"
//		+"	2 -- radial basis function: exp(-gamma*|u-v|^2)\n"
//		+"	3 -- sigmoid: tanh(gamma*u'*v + coef0)\n"
//		+"	4 -- precomputed kernel (kernel values in training_set_file)\n"
//		+"-d degree : set degree in kernel function (default 3)\n"
//		+"-g gamma : set gamma in kernel function (default 1/num_features)\n"
//		+"-r coef0 : set coef0 in kernel function (default 0)\n"
//		+"-c cost : set the parameter C of C-SVC, epsilon-SVR, and nu-SVR (default 1)\n"
//        
//		+"-m cachesize : set cache memory size in MB (default 100)\n"
//		+"-e epsilon : set tolerance of termination criterion (default 0.001)\n"
//		+"-h shrinking : whether to use the shrinking heuristics, 0 or 1 (default 1)\n"
//		+"-b probability_estimates : whether to train a SVC or SVR model for probability estimates, 0 or 1 (default 0)\n"
//		+"-wi weight : set the parameter C of class i to weight*C, for C-SVC (default 1)\n"
//		+"-v n : n-fold cross validation mode\n"
//		+"-q : quiet mode (no outputs)\n"
        _file_model = _file_scale_train + ".model";
        float g, c;
        g = (float) Math.pow(2, 2);
        c = (float) Math.pow(2, 3);
        String[] args = new String[]{"-s", "0", "-t", "2", "-g", Float.toString(g), "-c", Float.toString(c), "-b", "1", "-v", "5", _file_scale_train, _file_model};
        svm_train.main(args);
    }

    private void testing() throws IOException {
//        usage: svm_predict [options] test_file model_file output_file\n"
//		+"options:\n"
//		+"-b probability_estimates: whether to predict probability estimates, 0 or 1 (default 0); one-class SVM not supported yet\n"
//		+"-q : quiet mode (no outputs)
        String[] args = new String[]{"-b", "0", _file_scale_test, _file_model, _file_output};
        svm_predict.main(args);
    }

    private void runing() throws IOException {
        scaling();
        training();
    }

    public static double runIndirect(String type) throws IOException {
        _file_train = "src\\Data\\train" + type;
        _file_test = "src\\Data\\test" + type;
        //Note: File train & test must be using same factors.
        _file_scale_train = _file_train + ".scale";
        _file_scale_test = _file_test + ".scale";
        _file_scale_para = _file_train + ".para";
        _file_model = "";
        _file_output = _file_train + "output";
        IndirectMain i = new IndirectMain();
        i.runing();
        return cva;
    }

    private static void scalingForTest() throws IOException {
//        "Usage: svm-scale [options] data_filename\n"
//		+"options:\n"
//		+"-l lower : x scaling lower limit (default -1)\n"
//		+"-u upper : x scaling upper limit (default +1)\n"
//		+"-y y_lower y_upper : y scaling limits (default: no y scaling)\n"
//		+"-s save_filename : save scaling parameters to save_filename\n"
//		+"-r restore_filename : restore scaling parameters from restore_filename\n"
//              +"-o output_filename : save scaling output to output_filname"
        String[] args = new String[]{"-l", "0", "-u", "1", "-s", _file_scale_para, "-o ", _file_scale_train, _file_train};
        svm_scale.main(args);
        args = new String[]{"-l", "0", "-u", "1", "-r", _file_scale_para, "-o ", _file_scale_test, _file_test};
        svm_scale.main(args);
    }

    static void trainingForTest() throws IOException {
        _file_model = _file_scale_train + ".model";
        float g, c;
        g = (float) Math.pow(2, 2);
        c = (float) Math.pow(2, 3);
        String[] args = new String[]{"-s", "0", "-t", "2", "-g", Float.toString(g), "-c", Float.toString(c), _file_scale_train, _file_model};
        svm_train.main(args);
    }

    public static void runningForTest(String type) throws IOException {
        _file_train = "src\\Data\\train" + type;
        _file_test = "src\\Data\\test" + type;
        _file_scale_train = _file_train + ".scale";
        _file_scale_test = _file_test + ".scale";
        scalingForTest();
    }

    private void runingForSentence() throws IOException {
        scalingForTest();
        trainingForTest();
        testing();
    }

    public static void runIndirectForSentence(String type) throws IOException {
        _file_train = "src\\Data\\train" + type;
        _file_test = "src\\Data\\testSentence" + type;;
        //Note: File train & test must be using same factors.
        _file_scale_train = _file_train + ".scale";
        _file_scale_test = _file_test + ".scale";
        _file_scale_para = _file_train + ".para";
        _file_model = "";
        _file_output = "src\\Data\\output" + type;
        IndirectMain i = new IndirectMain();
        i.runingForSentence();
    }
}
