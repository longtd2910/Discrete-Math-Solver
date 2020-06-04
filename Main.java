import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Danh sách ký tự đặc biệt
    public final static Character[] specialCharacter = { '(', ')', '^', 'v', '+', '=', '>', '-' , '0'};

    // Danh sách toán tử
    public final static Character[] specialOperator = { '-', '^', 'v', '>', '+', '=' };

    // Lưu phép toán
    private static String mainProb;

    // Lay cac thuoc tinh cua ngoac
    private static int firstIndex;
    private static int lastIndex;
    private static int lengthMath;

    // Lấy ngoặc trong cùng và tính kết quả
    public static int smallestBracketResult() {
        firstIndex = 0;
        lastIndex = 0;
        lengthMath = 0;

        // Tach lay bieu thuc trong ngoac
        String smallProb = "";
        for (int i = mainProb.length() - 1; i >= 0; --i) {
            if (mainProb.charAt(i) == '(') {
                for (int j = i; j < mainProb.length(); ++j) {
                    if (mainProb.charAt(j) == ')') {
                        for (int k = i + 1; k < j; ++k) {
                            smallProb += String.valueOf(mainProb.charAt(k));
                            lengthMath++;
                        }
                        Calculate worker = new Calculate();
                        boolean operatorAvailable = true;
                        while (operatorAvailable) {
                            operatorAvailable = false;
                            for (int a = 0; a < 6; ++a) {
                                for (int b = 0; b < smallProb.length(); ++b) {
                                    if (smallProb.charAt(b) == specialOperator[a]) {
                                        operatorAvailable = true;
                                        int value1 = Integer.parseInt(String.valueOf(smallProb.charAt(b - 1)));
                                        int value2 = Integer.parseInt(String.valueOf(smallProb.charAt(b + 1)));
                                        String result = String.valueOf(worker.calcProb(String.valueOf(smallProb.charAt(b)), value1, value2));
                                        StringBuilder sb = new StringBuilder(smallProb);
                                        sb.replace(b - 1, b + 2, result);
                                        smallProb = sb.toString();
                                        b = 0;
                                    }
                                }
                            }
                        }
                        firstIndex = i;
                        lastIndex = firstIndex + lengthMath;
                        break;
                    }
                }
                break;
            }
        }
        lengthMath -= 2;
        // Tinh toan bieu thuc trong ngoac

        return Integer.parseInt(smallProb);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Lấy phương trình
        mainProb = "p^(qVr^(QVR^Q))Vq^r";
        mainProb = sc.nextLine();
        mainProb = mainProb.toLowerCase();

        // Tìm các biến trong phương trình
        ArrayList<Character> ele = new ArrayList<>();
        for (int i = 0; i < mainProb.length(); ++i) {
            boolean isSpecial = false;
            for (int j = 0; j < 9; ++j) {
                if (mainProb.charAt(i) == specialCharacter[j]) {
                    isSpecial = true;
                    break;
                }
            }
            if (ele.contains(mainProb.charAt(i)) == false && isSpecial == false) {
                ele.add(mainProb.charAt(i));
            }
        }

        // Thay các biến bằng các giá trị 1-0 nhập vào
        for (Character element : ele) {
            System.out.print(element + "= ");
            mainProb = mainProb.replace(element, (char) (sc.nextInt() + 48));
        }
        sc.close();
        int bracketCount = 0;
        for (int i = 0; i < mainProb.length(); ++i) {
            if (mainProb.charAt(i) == '(') {
                ++bracketCount;
            }
        }
        for (int i = 0; i < bracketCount; ++i) {
            int a = smallestBracketResult();
            StringBuilder sb = new StringBuilder(mainProb);
            sb.replace(firstIndex, lastIndex + 2, String.valueOf(a));
            mainProb = sb.toString();
        }
        if (mainProb != "0" || mainProb != "1") {
            Calculate worker = new Calculate();
            boolean operatorAvailable = true;
            while (operatorAvailable) {
                operatorAvailable = false;
                for (int a = 0; a < 6; ++a) {
                    for (int b = 0; b < mainProb.length(); ++b) {
                        if (mainProb.charAt(b) == specialOperator[a]) {
                            operatorAvailable = true;
                            int value1 = Integer.parseInt(String.valueOf(mainProb.charAt(b - 1)));
                            int value2 = Integer.parseInt(String.valueOf(mainProb.charAt(b + 1)));
                            String result = String.valueOf(worker.calcProb(String.valueOf(mainProb.charAt(b)), value1, value2));
                            StringBuilder sb = new StringBuilder(mainProb);
                            sb.replace(b - 1, b + 2, result);
                            mainProb = sb.toString();
                            b = 0;
                        }
                    }
                }
            }
        }
        System.out.println(mainProb);
    }
}