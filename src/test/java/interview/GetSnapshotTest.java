package interview;

import org.junit.Assert;
import org.junit.Test;

public class GetSnapshotTest {

    String demoHistoryData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\n" +
            "cat1 10 9\n\n" +
            "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\n" +
            "cat1 10 9 2 -1\n" +
            "cat2 2 3\n\n" +
            "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\n" +
            "cat1 12 8 3 4";

    String demoHistoryDataWrong = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016-09-02 22:30:46\n" +
            "cat1 10 9\n\n" +
            "351055db-33e6-4f9b-bfe1-16f1ac446ac1\n2016/09/02 22:30:52\n" +
            "cat1 10 9 2 -1\n" +
            "cat2 2 3\n\n" +
            "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155\n2016/09/02 22:31:02\n" +
            "cat1 12 8 3 4";

    String demoHistoryConflictData = "e4e87cb2-8e9a-4749-abb6-26c59344dfee\n2016/09/02 22:30:46\n" +
            "cat1 10 9\n\n" +
            "351055db-33e6-4f9b-bfe1-16f1ac446ac1 2016/09/02 22:30:52\n" +
            "cat1 10 9 2 -1\n" +
            "cat2 2 3\n\n" +
            "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155 2016/09/02 22:31:02\n" +
            "cat1 11 8 3 4";

    String invalidMsg = "Invalid format.";

    @Test
    public void getSnapShot_OK() {

        GetSnapshot getSnapshot = new GetSnapshot();
        String snapShot = getSnapshot.getSnapShot(demoHistoryData, "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155");
        System.out.println(snapShot);
        Assert.assertEquals(snapShot, "cat1 15 12\n" +
                "cat2 2 3");
    }

    @Test
    public void getSnapShot_HistroyDataIsWrong() {
        GetSnapshot getSnapshot = new GetSnapshot();
        String snapShot = getSnapshot.getSnapShot(demoHistoryDataWrong, "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155");
        System.out.println(snapShot);
        Assert.assertEquals(snapShot, invalidMsg);
    }

    @Test
    public void getSnapShot_DataConflict() {
        GetSnapshot getSnapshot = new GetSnapshot();
        String snapShot = getSnapshot.getSnapShot(demoHistoryConflictData, "dcfa0c7a-5855-4ed2-bc8c-4accae8bd155");
        System.out.println(snapShot);
        Assert.assertEquals(snapShot, invalidMsg);
    }
}
