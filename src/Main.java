import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Set<String> blackList = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            blackList.add(reader.readLine());
        }
        int m = Integer.parseInt(reader.readLine());
        List<MyFile> allFiles = new ArrayList<>(m / 2);
        for (int i = 0; i < m; i++) {
            String fullName = reader.readLine();
            boolean inBlackList = false;
            for (String bl : blackList) {
                if (fullName.contains(bl)) {
                    inBlackList = true;
                    break;
                }
            }
            if (!inBlackList) {
                continue;
            }
            String ext = fullName.substring(fullName.lastIndexOf('.')).intern();
            MyFile my = new MyFile();
            my.setExt(ext);
            my.setName(fullName);
            allFiles.add(my);
        }
        int q = Integer.parseInt(reader.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            String path = reader.readLine();
            boolean found = false;
            Map<String, Integer> extMap = new HashMap<>(10);
            for (MyFile my : allFiles) {
                if (my.getName().length() < path.length()) {
                    continue;
                }
                if (my.getName().substring(0, path.length()).equals(path)) {
                    String ext = my.getExt();
                    Integer count = extMap.get(ext);
                    count = (count == null) ? 1 : count + 1;
                    extMap.put(ext, count);
                    found = true;
                }
            }
            if (!found) {
                sb.append(0);
                sb.append("\n");
            } else {
                sb.append(extMap.size());
                sb.append("\n");
                for (Map.Entry<String, Integer> entryExt : extMap.entrySet()) {
                    sb.append(entryExt.getKey() + ": " + entryExt.getValue());
                    sb.append("\n");
                }
            }
        }
        System.out.println(sb.toString());
        reader.close();
    }
}
class MyFile {
    private String root = "/";
    private String name = "";
    private String ext = "";

    public MyFile() {
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}