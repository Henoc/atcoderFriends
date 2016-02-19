package arc22;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.InputMismatchException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.MAX_VALUE;


public class C {
  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);
  static final int INF = MAX_VALUE / 10;
  static final double EPS = 1e-10;
  static int farthestVertex, farthestDist;
  static Edge[] edges;

  public static void main(String[] args) {
    int N = ni();
    edges = new Edge[N];
    for (int i = 0; i < N; i++) {
      edges[i] = new Edge();
    }
    for (int i = 0; i < N - 1; i++) {
      int a = ni() - 1, b = ni() - 1;
      edges[a].list.add(b);
      edges[b].list.add(a);
    }

    farthestVertex = -1;
    farthestDist = 0;
    dfs(0, -1, 0);
    int v1 = farthestVertex;
    farthestVertex = -1;
    farthestDist = 0;
    dfs(v1, -1, 0);
    int v2 = farthestVertex;
    out.println((v1 + 1) + " " + (v2 + 1));

    out.flush();
  }

  // farthestDist, farthestVertex を書き換える
  static void dfs(int x, int pre, int dist) {
    if(farthestDist < dist){
      farthestDist = dist;
      farthestVertex = x;
    }
    for(int e : edges[x].list){
      if(pre == e) continue;
      dfs(e, x, dist + 1);
    }
  }

  static class Edge {
    ArrayList<Integer> list = new ArrayList<>();
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
