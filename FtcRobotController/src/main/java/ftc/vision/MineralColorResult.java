package ftc.vision;

import org.opencv.core.Scalar;

public class MineralColorResult {
    private final MineralColor left, center, right;

    public MineralColorResult(MineralColor left, MineralColor center, MineralColor right) {
        this.left = left;
        this.center = center;
        this.right = right;
    }

    public MineralColor getLeft() {
        return left;
    }

    public MineralColor getCenter() {
        return center;
    }

    public MineralColor getRight() {
        return right;
    }

    public enum MineralColor{
        GOLD (ImageUtil.YELLOW),
        UNKNOWN(ImageUtil.BLACK);

        public final Scalar color;

        MineralColor(Scalar color){
            this.color = color;
        }
    }

    @Override
    public String toString() {
        return left + " , " + center + " , " + right;
    }
}
