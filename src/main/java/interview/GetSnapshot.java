package interview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sunyajun
 * @date 2020/3/3 4:21 PM
 */
public class GetSnapshot {


    public static void main(String[] args) {
        String historyData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\n" +
                "cat1 10 9\n\n" +
                "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\n" +
                "cat1 10 9 2 -1\n" +
                "cat2 2 3\n\n" +
                "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\n" +
                "cat1 12 8 3 4";
        GetSnapshot getSnapshot = new GetSnapshot();
        String snapShot = getSnapshot.getSnapShot(historyData, "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155");
        System.out.println(snapShot);

        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param historyData
     * @param id
     * @return
     */
    public String getSnapShot(String historyData, String id) {
        StringBuilder sbResult = new StringBuilder();
        // history和id是否为空
        if (ifEmpty(historyData) || ifEmpty(id)) {
            sbResult.append("Invalid format.");
            return sbResult.toString();
        }
        // 按行分隔
        String[] rows = historyData.split("\n");

        int step = 1;   // 用来记录当前处理到哪一步了 1: 时间id 2: 时间行 3: 处理位置数据
        Boolean ifFindTime = false; // 最终是否找到了id
        Map<String, ZooPos> snapshotPosMap = new TreeMap<>(); // 用来存放那一刻的快照 key为name, value为位置

        ifFindTime = searchSnapShotById(id, sbResult, rows, step, ifFindTime, snapshotPosMap);

        // 是否找到了结果
        if (ifFindTime) {
            // 输出结果
            Set<Map.Entry<String, ZooPos>> entries = snapshotPosMap.entrySet();
            if (entries.size() > 0) {
                for (Map.Entry<String, ZooPos> entry : entries) {
                    sbResult.append(entry.getKey() + " " + entry.getValue().getX() + " " + entry.getValue().getY() + "\n");
                }
            }
            return sbResult.subSequence(0, sbResult.length() - 1).toString();
        }
        return sbResult.toString();
    }

    private Boolean searchSnapShotById(String id, StringBuilder sbResult, String[] rows, int step, Boolean ifFindTime, Map<String, ZooPos> snapshotPosMap) {
        // 遍历处理每一行
        for (int i = 0; i < rows.length; i++) {
            String row = rows[i];

            // 是否空行
            if (ifEmpty(rows[i])) {
                // 空行的step必须是3
                if (step != 3) {
                    sbResult.append("Invalid format.");
                    break;
                }
                step = 1;
                continue;
            }

            if (step == 1) { // 步骤1 处理id
                // id是否为空
                if (ifEmpty(rows[i])) {
                    sbResult.append("Invalid format.");
                    break;
                }
                // 是否找到了该时间id
                if (row.equals(id)) {
                    ifFindTime = true;
                }

                step++;
                continue;
            } else if (step == 2) { // 步骤2：处理时间(只需要校验格式)
                // 格式化时间
                if (!isValidDate(row)) {
                    sbResult.append("Invalid format.");
                    break;
                }

                step++;
                continue;
            } else if (step == 3) { // 步骤3：处理位置数据
                // 格式应该这样cat2 2 3
                String[] words = row.split(" "); // 数据行中的每个数据
                if (words == null || words.length == 0) {
                    sbResult.append("Invalid format.");
                    break;
                }
                // cat1 10 9 2 -1格式不对
                if (words.length != 3 && words.length != 5) {
                    sbResult.append("Invalid format.");
                    break;
                }

                String name = words[0];
                ZooPos zooPos = new ZooPos();
                zooPos.setName(name);

                // 获取位置行
                if (snapshotPosMap.containsKey(name)) { //
                    // 非第一次，必须是5个
                    if (words.length != 5) {
                        sbResult.append("Invalid format.");
                        break;
                    }
                    // 判断当前位置是否合法
                    ZooPos preZooPos = snapshotPosMap.get(name);
                    if (ifPosConflict(words, preZooPos)) {
                        sbResult.append("Conflict found at ").append(id);
                        break;
                    }

                    // 更新新的位置
                    zooPos.setX(Integer.parseInt(words[1]) + Integer.parseInt(words[3]));
                    zooPos.setY(Integer.parseInt(words[2]) + Integer.parseInt(words[4]));
                    snapshotPosMap.put(name, zooPos);
                    continue;
                } else {
                    // 第一次进来，必须是3
                    if (words.length != 3) {
                        sbResult.append("Invalid format.");
                        break;
                    }
                    zooPos.setX(Integer.parseInt(words[1]));
                    zooPos.setY(Integer.parseInt(words[2]));
                    snapshotPosMap.put(name, zooPos);
                    continue;
                }
            }

            // 此处步骤必须为3
            if (step != 3) {
                sbResult.append("Invalid format.");
                break;
            }

            // 是否最后一行
            if (i == rows.length - 1) {
                System.out.println("=====> 最后一行，没有该时间, row:" + i);
            }
        }
        return ifFindTime;
    }

    private boolean ifPosConflict(String[] words, ZooPos preZooPos) {
        return preZooPos.getX() != Integer.parseInt(words[1]) || preZooPos.getY() != Integer.parseInt(words[2]);
    }

    private boolean ifEmpty(String row) {
        return row == null || row.length() == 0;
    }

    public static boolean isValidDate(String str) {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //这里的时间格式根据自己需求更改（注意：格式区分大小写、格式区分大小写、格式区分大小写）
        try {
            Date date = (Date) formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }
}
