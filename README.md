# 筋トレ記録システム     
## システム名：トレコード     

### 概要    
本Webシステムは、トレーニング内容を記録し利用者の運動習慣、モチベーションアップのサポートをします。

### 使用技術    
- バックエンド     
    - Java    
    - MySQL     
- フロントエンド
    - HTML
    - CSS     
    - JavaScript(jQuery)    
- コード管理    
    - Fork        
    - GitHub
 
### 言語使用率
- Java 54.7%
- HTML 27.4%
- CSS 6.9%
- JavaScript 11.0%

### 制作者（五十音順）
- 荒牧（担当：体重・BMI・トレーニング記録、グラフ表示）
- 大串（担当：写真記録、アルバム表示、カレンダー表示）
- 久野（担当：フロントエンド、ガチャ機能）
- 山田（担当：ログイン・ログアウト・ユーザー登録、アイコン表示）


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
- 分かりやすく、記憶に残りやすい       
- 他社筋トレサービスと被らないため、検索で上位にヒットしやすい        

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

![trecord3-2](https://github.com/Erina-Aramaki/TRECORD/assets/75921588/af484ec7-5e18-477e-956b-49434376ac40)

### データ設計
![trecord4](https://github.com/Erina-Aramaki/TRECORD/assets/75921588/cc4d62b9-c225-4a1b-98f2-e1a281a55b88)

### ワイヤーフレーム
![ワイヤーフレーム_TOP](https://github.com/Erina-Aramaki/TRECORD/assets/75921588/7cf1cf7b-7384-4ace-a7c1-1ecf749fd05e)
![ワイヤーフレーム_マイページ](https://github.com/Erina-Aramaki/TRECORD/assets/75921588/e707b899-5c4d-4cf1-86d8-e68bf570825d)

### 工夫したこと     
- 進捗管理       
  WBSとガントチャートにより進捗管理をした
- 画面遷移図        
　ラフプロトタイプを作成し、各画面の動きや流れを整理した
- UI/UX設計
  利用者のペルソナを設定しゴールを明確化、UI/UX設計をすることでシステム全体の整合性が取れるようにした
- ユーザー登録       
    - 画像登録がnullの場合、デフォルトの画像を表示        
    - 1つのHTMLを複数の処理で使いまわせる設計       
- コンフリクト対策      
　各メンバーそれぞれのController、HTMLで制作を進め、最終的に結合を行った
- グラフ作成       
  List<>データを取得後、List<List<>>のデータを一つ一つ取り出した            
↓リスト型のクラスを入れ子にして渡す（Java）
![List1](https://github.com/Erina-Aramaki/WhatDoYouEatToday/assets/75921588/a83ad93d-1468-4f0b-a68f-e75bf9bba685)

↓入れ子になったデータをインデックス指定で受け取る（JavaScript）
![List2](https://github.com/Erina-Aramaki/WhatDoYouEatToday/assets/75921588/8ab80bd6-8c59-484a-9ce6-18a310a7e45e)


### 反省点      
- 初期設計         
　各機能の詳細設計が甘く、製作途中で機能の追加や変更が多々あったため、その都度軌道修正した。もっと内容を詰めるべきだった。
-  コミュニケーション不足による認識の齟齬              
  進捗管理はWBSとガントチャートにそれぞれ記入してもらうことで管理できると考えていたが、                    
実際は更新がされていないことも多々あった。             
 ツールだけに頼るのではなく、一人一人に確認した上で活用することが大事だと感じた。
- 優先順位               
  優先度は機能のみで考えていたが、機能・画面・コントローラーの3つの観点で考え、最初に認識のすり合わせをすべきだった。
- スケジュール調整            
  優先順位の高い機能の実装が遅れたことによりスケジュール全体が1週間押してしまった。     


### 今後実装予定    
- パスワードのハッシュ化
- 記録の編集、削除
- 筋トレメニュー診断機能
- ログイン認証


### 感想    
- 1人で制作する際は自分1人の理想を詰め込めば良かったが、チーム開発ではチームメンバー全員の理想を詰め込み、データの受け渡しの流れを考慮する必要があったため、認識のすり合わせに多くの時間を要し、苦戦を強いられた。
- チーム開発の問題の発生、解決を通して、チームワークの重要性を実感できた。
- 実際に業務で開発をする前に学校でチーム制作をすることができ、良い経験になった。
