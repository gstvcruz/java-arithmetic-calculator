import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws Exception {
    NotationConverter nc = new NotationConverter("10 + (2 * 3 - 4) ^ 2 / 4 + 6 * 2");
    StringQueue postFixExp = nc.getPostFixNotation();
  }
}
