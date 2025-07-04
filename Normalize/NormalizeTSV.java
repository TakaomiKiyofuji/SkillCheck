import java.io.*;

/**
 * 【第一正規化プログラム】
 * 
 * 入力：TSV形式のデータを標準入力から読み込み、
 * 各セルに含まれる「:（コロン）」区切りの複数値を個別行に展開。
 * 出力：第一正規化されたTSVを標準出力へ出力。
 * 
 * コマンドライン引数：なし（標準入力/出力を使用）
 * 
 * 制約：
 * - 列数は全行で統一（最大5列）
 * - 各セルの長さは最大10,000文字（空文字含む）
 * - セル内の値は最大10個（:で区切られる）
 * - 行数無制限
 * - 文字はASCII印字可能文字（0x20〜0x7E）
 */
public class NormalizeTSV {

    public static void main(String[] args) throws IOException {
        // 標準入力から読み込み
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = reader.readLine()) != null) {
            // 各行をタブで分割（空のセルも保持）
            String[] columns = line.split("\t", -1);

            // セルの分解結果を保持する配列（最大10個）
            String[][] splitCells = new String[columns.length][];
            int totalCombinations = 1;

            // 各セルごとに「:」で分割
            for (int i = 0; i < columns.length; i++) {
                // セルの検証（文字数や文字種）
                if (!isValidCell(columns[i])) {
                    System.err.println("Error: 無効なセル値が含まれています");
                    return;
                }

                // 空文字なら1要素として扱う（空文字のみ）
                if (columns[i].isEmpty()) {
                    splitCells[i] = new String[]{""};
                } else {
                    splitCells[i] = columns[i].split(":", -1);
                }

                // 最大10個まで
                if (splitCells[i].length > 10) {
                    System.err.println("Error: セル内の値が10個を超えました");
                    return;
                }

                totalCombinations *= splitCells[i].length;
            }

            // 多重ループ的に組み合わせを生成して出力（第一正規化）
            for (int row = 0; row < totalCombinations; row++) {
                int[] indices = getCombinationIndices(splitCells, row);
                StringBuilder output = new StringBuilder();

                for (int col = 0; col < splitCells.length; col++) {
                    if (col > 0) output.append("\t");
                    output.append(splitCells[col][indices[col]]);
                }

                System.out.println(output.toString());
            }
        }
    }

    /**
     * 各セルに含まれる値の組み合わせインデックスを計算
     * 
     * 例：A(2要素), B(3要素) → 2×3 = 6行に展開
     */
    private static int[] getCombinationIndices(String[][] splitCells, int row) {
        int[] indices = new int[splitCells.length];
        int divisor = 1;
        for (int i = splitCells.length - 1; i >= 0; i--) {
            int len = splitCells[i].length;
            indices[i] = (row / divisor) % len;
            divisor *= len;
        }
        return indices;
    }

    /**
     * セルが仕様に従っているかを検証
     * - 最大10000文字
     * - ASCII印字可能文字のみ（0x20〜0x7E）
     */
    private static boolean isValidCell(String cell) {
        if (cell.length() > 10000) return false;
        for (char c : cell.toCharArray()) {
            if (c < 0x20 || c > 0x7e) return false;
        }
        return true;
    }
}
