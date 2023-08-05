# PLACE
英単語アプリ "PLACE" (Plactice, Learning and Confront English）一旦fixバージョン  
4選択問題形式で英単語を学習できる．

# 動作確認環境
Android10, Android11

# Firebase の設定方法

[firebase 設定](doc/firebase_setting.md)

# アプリ使い方
## サインイン　or サインアップ
<image height="400" src="https://user-images.githubusercontent.com/53263220/106417633-e7feb200-6497-11eb-87c3-c6f800b6ab64.png"> <image height="400" src="https://user-images.githubusercontent.com/53263220/106417635-e92fdf00-6497-11eb-80b2-59d550ef8a5d.png">  
起動するとサインイン画面が出てきます．アカウントを持っている場合は入力してサインインしてください．  
もし，アカウントがなければサインアップを押してもらうとサインアップ画面(画像右)に飛びます.必要事項を入力するとサインアップが完了します．  
サインインまたは，サインアップをするとホーム画面へ行きます．
  
  
## ホーム画面
<image height="400" src="https://user-images.githubusercontent.com/53263220/106417636-e9c87580-6497-11eb-8957-22e2e9a0eb97.png">  
ホーム画面からできることは3つです．  
  
-  "START"を押して計測を開始する(基本は10分の計測です)．(ただし，"metadata"を計測前に開いてないとはじめられません．)  
-  "METADATA"を押して計測に必要なデータを入力する．  
-  右上の"⁝"を押して，configで計測時間の変更，サインアウト等を行う．  
  
## メタデータ入力画面
<image height="400" src="https://user-images.githubusercontent.com/53263220/106417640-eaf9a280-6497-11eb-885d-898eb85bfb73.png">  
 入力項目は以下のようになっています．  
  
  -  ラベルデータ:  
     計測する際の，ユーザの姿勢や行動を選んでください．walk_treadmillはウォーキングマシン上でwalk_disturbは人込みなどを歩くことを想定しています．それ以外の場合はotherとしてください．      
  -  問題パターン:  
  今回計測に使用する英単語問題のパターンを選んでください．各パターンに含まれる英単語は30語です．パターンの指定がない場合はotherを選択してください．　　
  - その他のメタデータ等:  
  上記以外に，記録すべきことがあれば書いてください．  
  
  項目を埋めたら保存を押すことで，メタデータが保存されホーム画面へ戻ります．  
  

## 計測画面（英単語学習画面）と結果画面
<image height="400" src="https://user-images.githubusercontent.com/53263220/106417637-ea610c00-6497-11eb-9ca8-a8ec8afc1921.png"> <image height="400" src="https://user-images.githubusercontent.com/53263220/106417642-eaf9a280-6497-11eb-9cd6-a74cac00993c.png">  

計測が始まると左図のような画面になります．英単語学習問題の形式は4選択問題で，上のテキストの意味に合うような，選択肢を選ぶものになります．  
  最終的に30単語が終わると結果画面(右図)になります．RESTARTを押すことで，同じ問題で英単語学習問題が始まります．  
  左上の家のボタンを押すと計測を中断するか聞かれます，中断した場合はホーム画面へ戻ることができます．
  
## 計測の終了
<image height="400" src ="https://user-images.githubusercontent.com/53263220/106420090-40847e00-649d-11eb-8ce6-12361e0b1af2.png">  
  計測が終了すると，図のような画面が出てきます．この画面が出た場合無事に計測が終了したことを意味します．  
  計測終了や経過時間を知るすべは実験の性質上これだけですので見逃さないようにお願いします．
  
## 計測の中断
<image height="400" src="https://user-images.githubusercontent.com/53263220/106420660-71b17e00-649e-11eb-8bab-b76430e4b91a.png">
もし，計測を始めてから10分以内に中断したくなった場合は，バックボタンなどを押すことで中断をすることができます(図のようなダイアログが出ます)．  ただし，中断すると計測がリセットされるため注意してください．

# フレームワーク等
Firebase (Authentication, Firestore)
