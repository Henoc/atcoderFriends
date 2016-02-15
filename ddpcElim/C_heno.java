package discoveryContest;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.MAX_VALUE;

/**
 * Created by heno on 2016/02/12.
 */
public class Cj {
  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);
  static final int INF = MAX_VALUE / 10;
  static final double EPS = 1e-10;
  static String s;
  static int k;
  static SBComparator sbc = new SBComparator();

  public static void main(String[] args) {
    s = ns();
    k = ni();

    // dp(i, j) := i番目未満未処理, j回操作後の条件での辞書順最小文字列. ex. dp(s.length(), 0) := すべて未処理, 操作回数0
    // 文字列の後ろから処理する
    StringBuilder[][] dp = new StringBuilder[s.length() + 1][k + 1];
    for (int i = 0; i < s.length() + 1; i++) {
      for (int j = 0; j < k + 1; j++) {
        dp[i][j] = new StringBuilder("INF");
      }
    }
    dp[s.length()][0] = new StringBuilder("");

    for (int i = s.length(); i >= 0; i--) {
      for (int j = 0; j <= k; j++) {
        // そのまま(左)
        if(i - 1 >= 0) dp[i - 1][j] = min(newSB(dp[i][j]).insert(0, s.charAt(i-1)), dp[i-1][j]);
        // 置換, 削除(左下)
        if(i - 1 >= 0 && j + 1 <= k) {
          dp[i-1][j+1] = min(newSB(dp[i][j]).insert(0, 'a'), dp[i-1][j+1]);
          dp[i-1][j+1] = min(newSB(dp[i][j]), dp[i-1][j+1]);
        }
        // 挿入(下)
        if(j + 1 <= k) dp[i][j+1] = min(newSB(dp[i][j]).insert(0, 'a'), dp[i][j+1]);
      }
    }

    Arrays.sort(dp[0], sbc);
    out.println(dp[0][0]);
    out.flush();
  }

  static StringBuilder min(StringBuilder a, StringBuilder b) {
    return sbc.compare(a,b) > 0 ? b : a;
  }

  static StringBuilder newSB(StringBuilder a) {
    return a.toString().equals("INF") ? new StringBuilder() : new StringBuilder(a);
  }

  static int ni() {
    return in.readInt();
  }

  static long nl() {
    return in.readLong();
  }

  static String ns() {
    return in.readString();
  }

  static double nd() {
    return parseDouble(in.readString());
  }

  static class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[102400];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
      this.stream = stream;
    }

    public final int read() {
      if (numChars == -1)
        throw new InputMismatchException();
      if (curChar >= numChars) {
        curChar = 0;
        try {
          numChars = stream.read(buf);
        } catch (IOException e) {
          throw new InputMismatchException();
        }
        if (numChars <= 0)
          return -1;
      }
      return buf[curChar++];
    }

    public final int readInt() {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      int res = 0;
      do {
        if (c < '0' || c > '9')
          throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = read();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public final long readLong() {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      long res = 0;
      do {
        if (c < '0' || c > '9')
          throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = read();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public final String readString() {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = read();
      } while (!isSpaceChar(c));
      return res.toString();
    }

    public final static boolean isSpaceChar(int c) {
      return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
  }
}

class SBComparator implements Comparator<StringBuilder> {
  public int compare(StringBuilder o1, StringBuilder o2) {
    if(o1.toString().equals("INF") && o2.toString().equals("INF")) return 0;
    if(o1.toString().equals("INF")) return 1;
    if(o2.toString().equals("INF")) return -1;
    return o1.toString().compareTo(o2.toString());
  }
}
