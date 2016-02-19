package arc30;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.MAX_VALUE;

/**
 * Created by heno on 2016/02/19.
 */
public class B {
  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);
  static final int INF = MAX_VALUE / 10;
  static final double EPS = 1e-10;

  public static void main(String[] args) {
    int n = ni(), x = ni() - 1;
    boolean[] gem = new boolean[n];
    Edge[] graph = new Edge[n];
    for (int i = 0; i < n; i++) {
      graph[i] = new Edge();
    }

    for (int i = 0; i < n; i++) {
      gem[i] = ni() == 1;
    }
    for (int i = 0; i < n - 1; i++) {
      int a = ni() - 1;
      int b = ni() - 1;
      graph[a].list.add(b);
      graph[b].list.add(a);
    }

    out.println(dfs(x, -1 , graph, gem));

    out.flush();
  }

  static int dfs(int x, int pre, Edge[] graph, boolean[] gem) {
    int cost = 0;
    // xからいける頂点について
    for(int e : graph[x].list){
      if(e == pre) continue;
      int costE = dfs(e, x ,graph,gem);
      if(costE == 0 && gem[e]){
        cost += 2;
      }else if(costE != 0){
        cost += costE + 2;
      }
    }
    return cost;
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

class Edge {
  ArrayList<Integer> list = new ArrayList<>();
}