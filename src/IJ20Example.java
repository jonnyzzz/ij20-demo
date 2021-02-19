import java.util.Arrays;

public class IJ20Example {
    public static void main(String[] args) {
        var var = "var";
        callAMethod(var);

        for (int i = 0; i < args.length; i++) {
            for (int j = i+1; j < args.length; j++) {
                if (args[i].length() > args[j].length()) {
                    var t = args[i];
                    args[i] = args[j];
                    args[j] = t;
                }
            }
        }

        Arrays.stream(args).forEach(System.out::println);
    }

    static void callAMethod(String s) {
        System.out.println(s);
    }
}
