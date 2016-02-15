package codeFestival2014B;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringJoiner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.MAX_VALUE;

/**
 * Created by heno on 2016/02/15.
 */
public class C {
  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);
  static final int INF = MAX_VALUE / 10;
  static final double EPS = 1e-10;

  public static void main(String[] args) {
    String[] strings = new String[3];
    for (int i = 0; i < 3; i++) {
      strings[i] = ns();
    }
    // s1とs2から取り出す文字数
    int n = strings[0].length() / 2;

    // アルファベットが何個出てくるか のベクトルにする
    int[][] abcVector = new int[3][26];
    for (int i = 0; i < 3; i++) {
      abcVector[i] = strToVec(strings[i]);
    }

    boolean ans = true;

    // s3 - s2 <= s1 && |s3 - s2| <= N か判定 (s2から最大限取り出した場合の残りはs1から取り出せる)
    // s3 - s1 <= s2 && |s3 - s1| <= N か判定 (s1から最大限取り出した場合の残りはs2から取り出せる)
    int sub1 = 0, sub2 = 0;
    for (int i = 0; i < 26; i++) {
      if(!(abcVector[2][i] - abcVector[1][i] <= abcVector[0][i])) ans = false;
      if(!(abcVector[2][i] - abcVector[0][i] <= abcVector[1][i])) ans = false;
      sub1 += floor(abcVector[2][i] - abcVector[1][i], 0);
      sub2 += floor(abcVector[2][i] - abcVector[0][i], 0);
    }
    if(!(sub1 <= n)) ans = false;
    if(!(sub2 <= n)) ans = false;

    out.println(ans ? "YES" : "NO");

    out.flush();
  }

  static int floor(int a, int flr) {
    return a >= flr ? a : flr;
  }

  static int[] strToVec(String a) {
    int[] abcVector = new int[26];
    for (int i = 0; i < 26; i++) {
      abcVector[i] = 0;
    }

    for (int i = 0; i < a.length(); i++) {
      int charNo = a.charAt(i) - 'A';
      abcVector[charNo]++;
    }
    return abcVector;
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
