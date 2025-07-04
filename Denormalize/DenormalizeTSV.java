import java.io.*;
import java.util.*;

/**
 * 【第一正規化の逆変換プログラム】
 * 
 * 入力：キーと値の2列からなるTSV形式（標準入力から取得）
 * 出力：同じキーを持つ値を半角コロンで結合したTSV形式（標準出力へ出力）
 * 
 * ※このプログラムの出力を、第一正規化のプログラムに入力することで、
 *   元のTSV構造を再現することが可能。
 * 
 * 制約：
 * - 行数最大1000行
 * - 各セルはASCII印字可能文字（0x20〜0x7e）で0〜100文字
 * - 値のグループ化最大10個まで
 */
public class DenormalizeTSV {

    public static void main(String[] args) throws IOException {
        // 標準入力を読み込むReaderを作成
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 行数制限（1000行）を確認するためのカウント
        int rowCount = 0;

        // LinkedHashMapを使用し、順序を保持したままキーごとに値をまとめる
        Map<String, List<String>> grouped = new LinkedHashMap<>();

        String line;
        while ((line = reader.readLine()) != null) {
            rowCount++;
            if (rowCount > 1000) {
                System.err.println("Error: 行数が1000を超えました。");
                return;
            }

            // タブ区切りで分割（空の文字列も保持）
            String[] columns = line.split("\t", -1);
            if (columns.length != 2) {
                System.err.println("Error: 列数が2ではありません。");
                return;
            }

            String key = columns[0];
            String value = columns[1];

            // 各セルの文字数と文字コードをチェック（0x20〜0x7e）
            if (!isValidCell(key) || !isValidCell(value)) {
                System.err.println("Error: 無効な文字が含まれています。");
                return;
            }

            // 最大10個まで値を追加
            grouped.computeIfAbsent(key, k -> new ArrayList<>());
            if (grouped.get(key).size() < 10) {
                grouped.get(key).add(value);
            }
        }

        // 出力：キー + タブ + 値をコロンで連結
        for (Map.Entry<String, List<String>> entry : grouped.entrySet()) {
            String joined = String.join(":", entry.getValue());
            System.out.println(entry.getKey() + "\t" + joined);
        }
    }

    /**
     * セルの中身が仕様に合っているかどうかを検証
     * - 長さ：0〜100文字
     * - 含まれる文字：ASCII印字可能文字（0x20〜0x7e）
     */
    private static boolean isValidCell(String s) {
        if (s.length() > 100) return false;
        for (char c : s.toCharArray()) {
            if (c < 0x20 || c > 0x7e) return false;
        }
        return true;
    }
}
