【課題概要】
本プロジェクトは、Javaを使用して以下2つの処理を行うプログラムを実装しています：

1. 第一正規化（Normalize）
　- TSVデータ内の複数値セル（コロン区切り）を、1セル1値の行に展開します。

2. 第一正規化の逆変換（Denormalize）
　- キーと値の2列からなるTSVを読み込み、同一キーを持つ値をコロンで連結して1行にまとめます。

【フォルダ構成】
- Normalize/
  - NormalizeTSV.java：第一正規化処理のソースコード
  - input.tsv：正規化前のサンプル入力
  -  run_Normalize.bat：コンパイルおよび実行用バッチファイル
  - README.txt：第一正規化についての説明

- Denormalize/
  - DenormalizeTSV.java：逆正規化処理のソースコード
  - input.tsv：逆正規化前のサンプル入力
  - run_Denormalize.bat：コンパイルおよび実行用バッチファイル
  - README.txt：逆正規化についての説明

【前提環境】
- OS：Windows 10 または 11
- Java：JDK 17 以上
- 実行方法：各フォルダ内の.batファイルをダブルクリックしてください。
  -  run_Normalize.bat：NormalizeTSV.java を実行
  - run_Denormalize.bat：DenormalizeTSV.java を実行

【設計方針】
- 各プログラムは独立して動作します（2つの別プログラムとして構成）。
- コマンドラインからの操作を不要にするため、バッチファイルを用意。
- 処理の説明とメンテナンス性を考慮し、適切なインデントとコメントを記載。

【仕様からの逸脱事項】
- 仕様に従って実装済みです。
- 文字数制限や区切り数など、仕様上の制限に収まるよう十分に考慮して実装しています。

【備考】
- 入出力ファイルは UTF-8 エンコーディングで保存してください。
- 標準入力・標準出力にも対応可能ですが、今回は分かりやすさを優先しファイル入出力形式としています。


【ディレクトリ階層】
SkillCheck/
├─ 📁 Normalize
│  ├─ NormalizeTSV.java         // 第一正規化のソースコード
│  ├─ input.tsv                 // 正規化前サンプルデータ
│  ├─ run_Normalize.bat         // 実行用バッチファイル
│  └─ README.txt                // Normalize の説明書き
│
├─ 📁 Denormalize
│  ├─ DenormalizeTSV.java       // 逆正規化のソースコード
│  ├─ input.tsv                 // 逆正規化前サンプルデータ
│  ├─ run_Denormalize.bat       // 実行用バッチファイル
│  └─ README.txt                // Denormalize の説明書き
│
└─ README.txt                   // 全体用の総合 README　※このファイル
