import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class s40230415_detector {

    public static String readFileContent(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        char[] buffer = new char[10];
        while (reader.read(buffer) != -1) {
            stringBuilder.append(new String(buffer));
            buffer = new char[10];
        }
        reader.close();

        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        String filePath1 = args[0];
        String filePath2 = args[1];

        String input1 = readFileContent(filePath1);
        String input2 = readFileContent(filePath2);

        PlagiarismDetector plagiarismDetector = new PlagiarismDetector();
        System.out.println(plagiarismDetector.get(input1, input2));
        int result = plagiarismDetector.get(input1, input2) > 0.40 ? 1 : 0;
        System.out.println(result);
    }
}

class Utils {

    public static String CR = "\r";
    public static String EL = "\n";

    public static String[] getStringWithoutCommentsAndWhitespace(String source) {
        return removeWhiteSpace(removeComments(source));
    }

    private static String removeComments(String source) {
        StringBuilder result = new StringBuilder();
        int len = source.length();
        int indicator = 0;
        for (int i = 0; i < len; i++) {
            if (i < (len - 1) && source.charAt(i) == '/' && source.charAt(i + 1) == '*') {
                indicator += 1;
            } else if (i > 0 && source.charAt(i - 1) == '*' && source.charAt(i) == '/') {
                indicator -= 1;
            } else if (indicator == 0) {
                result.append(source.charAt(i));
            }
        }
        return removeInLineComments(result.toString());
    }

    private static String removeInLineComments(String source) {
        StringBuilder result = new StringBuilder();
        int len = source.length();
        for (int i = 0; i < len; ++i) {
            if (i < (len - 1) && source.charAt(i) == '/' && source.charAt(i + 1) == '/') {
                for (int j = i + 2; j < len; j++) {
                    String charString = String.valueOf(source.charAt(j));
                    if (EL.equals(charString) || CR.equals(charString)) {
                        break;
                    }
                }
            } else {
                result.append(source.charAt(i));
            }
        }
        return result.toString();
    }

    private static String[] removeWhiteSpace(String source) {
        if (source.length() == 0) {
            return null;
        }
        String temp = source.replace(".", "").replace(",", "").replace("?", "").replace("!", "");
        String[] cleanstr = temp.split(" ");
        return cleanstr;
    }
}

class PlagiarismDetector {
    public double get(String str1, String str2) {
        String[] cleanStr1 = Utils.getStringWithoutCommentsAndWhitespace(str1);
        if (cleanStr1.length == 0) {
            return 0;
        }

        String[] cleanStr2 = Utils.getStringWithoutCommentsAndWhitespace(str2);
        if (cleanStr2.length == 0) {
            return 0;
        }
        return (1.0 * getLCS(cleanStr1, cleanStr2)) / Math.min(cleanStr1.length, cleanStr2.length);
    }

    private int getLCS(String[] cleanStr1, String[] cleanStr2) {
        final int len1 = cleanStr1.length;
        final int len2 = cleanStr2.length;
        final int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (cleanStr1[i - 1].equals(cleanStr2[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[len1 - 1][len2 - 1];
    }
}