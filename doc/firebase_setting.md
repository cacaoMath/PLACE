# 自分のアカウントで Firebase を初期設定する方法

## Firebase のプロジェクト作成

1. firebase のコンソール画面でプロジェクトを追加する。プロジェクト名などは任意のものでいい。

   ![Alt text](img/image.png)

2. Android アプリを追加する

   ![Alt text](img/image-1.png)

3. Android パッケージ名を"com.example.place"としてアプリを登録

   ![Alt text](img/image-2.png)

4. googlr-service.json をダウンロードして指示通りの位置に配置する

   ![Alt text](img/image-3.png)

5. Firebase SDK はバージョンが異なる場合はバージョンを Android アプリの方で合わせておく

   ![Alt text](img/image-4.png)

6. あとは指示通りにコンソールに進む

   ![Alt text](img/image-5.png)

## Firestore の設定

- アプリのデータ保存に必要

1.  コンソールから Firestore の設定画面に行きデータベースの作成をする

    ![Alt text](img/image-7.png)

2.  モードはとりあえずテストモードでいい

    ![Alt text](img/image-6.png)

3.  ロケーションは自由で良し

    ![Alt text](img/image-8.png)

4.  設定完了するとこの画面に行きつく(いかなければ reload とかする)

    ![Alt text](img/image-9.png)

5.  ルールのところで"ルール json"のようにルールを設定する

    ![Alt text](img/image-10.png)
    <details><summary>ルール json</summary>

    ```json
    rules_version = '2';
    service cloud.firestore {
    match /databases/{database}/documents {

        match /users/{userId} {
        allow read, create: if request.auth != null;

        match /data/{dataId}{
        allow read, create: if request.auth.uid == userId;
        }
        }

        match /vocabulary/{userId} {
        allow read: if request.auth != null;

        match /data/{dataId}{
        allow read: if request.auth.uid == userId;
        }
        }

        match /androidResults/{userId} {
        allow read, write: if request.auth != null;

        match /MeasurementType/{MeasurementId}{
            allow read, write: if request.auth.uid == userId;

                match /Data/{DataId}{
                                allow read, create: if true;
                    }
        }
        match /data/{dataId}{
            allow read, create: if true;
        }
        }

        match /androidResults_MT/{userId} {
        allow read, write: if request.auth != null;

        match /MeasurementType/{MeasurementId}{
            allow read, write: if request.auth.uid == userId;

                match /Data/{DataId}{
                                allow read, create: if true;
                    }
        }
        match /data/{dataId}{
            allow read, create: if true;
        }
        }

        match /engTest/{documentId}{
            allow read, create: if true;
            match /data/{dataId}{
            allow read, create: if true;
            }
        }

        match /engTest_MT/{documentId}{
            allow read, create: if true;
            match /data/{dataId}{
            allow read, create: if true;
            }
        }

        match /engWriteTest/{userId}{
            allow read, create: if true;

        match /data/{dataId}{
            allow read, create: if true;
        }
        }
    }
    }
    ```

    </details>

## Authentication の設定

- ログインのために必要

1. Authentication で`始める`を押す(まだ設定したことがない場合)
   ![Alt text](img/image-12.png)

2. サインインメソッドで"メール/パスワード"を有効にする
   ![Alt text](img/image-13.png)
