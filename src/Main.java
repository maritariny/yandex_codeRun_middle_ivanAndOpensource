import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Set<String> blackList = new HashSet<>();
        Map<String, Map<String, Integer>> result = new HashMap<>();
        for (int i = 0; i < n; i++) {
            blackList.add(reader.readLine());
        }
        int m = Integer.parseInt(reader.readLine());
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
            String ext = fullName.substring(fullName.lastIndexOf('.'));
            List<String> allFolders = new ArrayList<>();
            int index = fullName.lastIndexOf("/");
            while (index != -1) {
                fullName = fullName.substring(0, index + 1);
                allFolders.add(fullName);
                index = fullName.lastIndexOf("/", fullName.length() - 2);
            }
            for (String path: allFolders) {
                Map<String, Integer> extensions = result.get(path);
                if (extensions == null) {
                    extensions = new HashMap<>();
                    extensions.put(ext, 1);
                } else {
                    Integer count = extensions.get(ext);
                    count = (count == null) ? 1 : count + 1;
                    extensions.put(ext, count);
                }
                result.put(path, extensions);
            }
        }
        int q = Integer.parseInt(reader.readLine());
        for (int i = 0; i < q; i++) {
            String path = reader.readLine();
            Map<String, Integer> ext = result.get(path);
            if (ext == null){
                System.out.println(0);
                continue;
            }
            System.out.println(ext.size());
            for (Map.Entry<String, Integer> entryExt : ext.entrySet()) {
                System.out.println(entryExt.getKey() + ": " + entryExt.getValue());
            }
        }
        reader.close();
    }
}