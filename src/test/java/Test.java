import com.bce.Main;
import com.bce.core.anime.model.ImageTree;
import com.bce.core.assets.TempAssets;
import com.bce.core.battle.Stage;

public class Test {
    static long st = 0, ed = 0;
    static long st_th = 0, ed_th = 0;
    static ImageTree a, b;

    public static void main(String[] args) {
        TempAssets.now_selecting_stageName = "stg000";

        Main.main(new String[1]);
    }
}
