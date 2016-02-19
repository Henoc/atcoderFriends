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

/**
 * WA になるやつ
 * 適当な頂点を始点としてそこからdfsして長いやつ2つが答え なのでは...
 * Created by heno on 2016/02/19.
 */
public class C {
  static InputReader in = new InputReader(System.in);
  static PrintWriter out = new PrintWriter(System.out);
  static final int INF = MAX_VALUE / 10;
  static final double EPS = 1e-10;

  public static void main(String[] args) {
    int N = ni();
    Edge[] edges = new Edge[N];
    for (int i = 0; i < N; i++) {
      edges[i] = new Edge();
    }
    for (int i = 0; i < N - 1; i++) {
      int a = ni() - 1, b = ni() - 1;
      edges[a].list.add(b);
      edges[b].list.add(a);
    }

    ArrayList<Ret> rets = new ArrayList<>();
    rets.add(new Ret(0,0));
    for(int to : edges[0].list){
      Ret tmp = dfs(0,to,edges);
      tmp.dist++;
      rets.add(tmp);
    }
    Collections.sort(rets);
    out.println((rets.get(0).v + 1) + " " + (rets.get(1).v + 1));

    out.flush();
  }

  static Ret dfs(int pre, int x, Edge[] edges) {
    Ret far = new Ret(x,0);
    for(int to : edges[x].list){
      if(to == pre) continue;
      Ret tmp = dfs(x,to,edges);
      tmp.dist++;
      if(tmp.dist > far.dist){
        far = tmp;
      }
    }
    return far;
  }

  static class Ret implements Comparable<Ret> {
    int v, dist;
    Ret(int v, int dist){
      this.v = v;
      this.dist = dist;
    }

    @Override
    public int compareTo(Ret o) {
      //降順
      return -(dist - o.dist);
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
