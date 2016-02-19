package codeFestival2014B;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.MAX_VALUE;

/**
 * Created by heno on 2016/02/19.
 */
public class D {
  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);
  static final int INF = MAX_VALUE / 10;
  static final double EPS = 1e-10;

  public static void main(String[] args) {
    int n = ni();
    HashMap<Integer, Integer> h = new HashMap<>(n + 2);
    int[] east = new int[n];
    int[] west = new int[n];
    for (int i = 0; i < n; i++) {
      h.put(i, ni());
    }
    h.put(-1, INF);
    h.put(n,  INF);

    // i から東に見て h[i] を超える高さの山の番号 j を求める (jはiから一番近いもの)
    for (int i = n - 1; i >= 0 ; i--) {
      int j = i + 1;
      while(h.get(j) <= h.get(i)){        // h[j] が低ければ
        j = east[j];                      // jから東に見てh[j]を超える高い山の番号を取得
      }
      east[i] = j;
    }

    // i から西に見て 以下同様
    for (int i = 0; i < n; i++) {
      int j = i - 1;
      while(h.get(j) <= h.get(i)) {
        j = west[j];
      }
      west[i] = j;
    }

    for (int i = 0; i < n; i++) {
      int ans = (east[i] - i - 1) + (i - west[i] - 1);
      out.println(ans);
    }

    out.flush();
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
