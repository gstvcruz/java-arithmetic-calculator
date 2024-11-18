public class Main {
  public static void main(String[] args) throws Exception {
    NotationConverter nc = new NotationConverter(args[0]);
    StringQueue postFixExp = nc.getPostFixNotation();
    Calculator calculator = new Calculator(postFixExp);
    System.out.println(calculator.getResult());
  }
}
