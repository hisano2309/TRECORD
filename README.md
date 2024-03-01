# 筋トレ記録システム
## システム名：トレコード

### 概要
本Webシステムは、トレーニング内容を記録し利用者の運動習慣、モチベーションアップのサポートをします。

### 使用技術
- バックエンド
    - Java(Spring boot)
    - MySQL
- フロントエンド
    - HTML
    - CSS
    - JavaScript(jQuery)
- コード管理
    - Fork
    - GitHub

### 言語使用率
- Java 42.9%
- HTML 38.7%
- JavaScript 9.6%
- CSS 8.8%

### 制作者（五十音順）
- 荒牧（担当：体重・BMI・トレーニング記録）
- 大串（担当：写真記録、アルバム表示、カレンダー表示）
- 久野（担当：基本設計、フロントエンド、ガチャ機能）
- 山田（担当：ログイン・ログアウト・ユーザー登録）
  計４名


 ***


### 背景
- 社会人になり運動習慣が無くなった方が多い
- 特に、エンジニアやWEBデザイナーなど在宅ワークの方は運動不足を感じている
- 男女ともに楽しみながら運動のモチベーションを保ってくれるアプリを開発したいと考えた

### 課題
- 多くの筋トレ記録アプリは「ガッツリ鍛えたい」方を対象にしているように感じるデザインのため、気が引けてしまう
- 初心者にとってはアプリをインストールしたり、ジムの契約をすることで満足してしまい、運動習慣が続かない

### 設計方針
- ゲームのようなワクワクする遊び心を取り入れて、初心者も楽しみながら成果を感じることができる
- 筋トレ初日と現在を比較することによって成長を実感できる

### 解決方法
- いつもと同じメニューに飽きてしまった際は、ガチャ機能によって気分転換を促す
- アルバム、グラフ表示など、成長を見える化することで利用者のモチベーションを上げる

### トレコード（TRECORD）とは？
- 本webシステム名「トレコード」は、「トレーニング」と「レコード（記録する）」の造語
- 名前だけで用途の想像ができ、記憶に残りやすい

### 利用者のペルソナ
- 運動不足な20～40代男女
- 筋トレに興味はあるけど何をしたらいいか分からない
- パーソナルジムに行くほどじゃないけど楽しく続けられるサポートは欲しい

### 機能一覧
- 基本情報
    - ユーザー登録、更新
    - ユーザー情報表示
    - ログイン、ログアウト

- エンタメ
    - 回数ガチャ
    - 重量ガチャ

- トレーニング
    - 記録
        - 身長、体重
        - マシン名、重量、回数、セット数
        - 写真
        - BMI計算
    - グラフ表示
        - 体重、BMI、適正体重
        - マシン使用率
        - トレーニング記録
    - 一覧表示
        - 体重、BMI
        - トレーニング記録
        - アルバム表示
    - カレンダー表示

### 進捗管理
![trecord1-2](https://github.com/Erina-Aramaki/TRECORD/assets/75921588/e306f608-bb9a-48b7-bda8-f2bcf8155ad8)

### 画面設計
![trecord2-2](https://github.com/Erina-Aramaki/TRECORD/assets/75921588/6c02ff70-c45b-4fdc-9245-e21b808dae09)
![image](https://github.com/hisano2309/TRECORD/assets/150416585/17ff3961-c392-486f-8b6c-f10e043b9191)



### データ設計
![trecord4](https://github.com/Erina-Aramaki/TRECORD/assets/75921588/cc4d62b9-c225-4a1b-98f2-e1a281a55b88)

### ワイヤーフレーム
![ワイヤーフレーム_TOP](https://github.com/Erina-Aramaki/TRECORD/assets/75921588/7cf1cf7b-7384-4ace-a7c1-1ecf749fd05e)
![ワイヤーフレーム_マイページ](https://github.com/Erina-Aramaki/TRECORD/assets/75921588/e707b899-5c4d-4cf1-86d8-e68bf570825d)

### 工夫したこと
- 進捗管理
  クラウド上でスケジュール管理表を作成・共有し全体の進捗管理をした
- 画面遷移図
  ラフプロトタイプを作成し、各画面の動きや流れを整理した
- UI/UX設計
  利用者のペルソナを設定しゴールを明確化、UI/UX設計をすることでシステム全体の整合性が取れるようにした
- コンポーネント設計
  作成したコンポーネントを複数の処理で使いまわせるよう設計した
- コンフリクト対策
  機能毎に役割分担をして制作を進めていき、最終的に結合を行った
- グラフ作成
  JSのライブラリを使用し、グラフを掲載することで記録内容を直感的にわかりやすく表示した

### 反省点
- 初期設計
  各自で行った機能の詳細設計が甘く、製作途中で機能の追加や変更が多々あったため、その都度全体のUI/UXデザインを軌道修正した。
- 優先順位
  必要な機能の優先順位をつけ重要度が高いものから制作取り掛かったが、使用する機能・画面の内容を共有し、制作工程の認識のすり合わせをすべきだった。

### 今後実装予定
- パスワードのハッシュ化
- 記録の編集、削除
- 筋トレメニュー診断機能
- レベルアップ機能

### 感想
- 1人で制作する際は自分1人の理想を詰め込めば良かったが、チーム開発ではチームメンバー全員の理想を詰め込み、データの受け渡しの流れを考慮する必要があったため、認識のすり合わせに多くの時間を要し、苦戦を強いられた。
- チーム開発での問題の発生、解決を通して、チームワークの重要性を実感できた。
