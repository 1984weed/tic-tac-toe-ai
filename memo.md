# 色々メモ

あとは外部から色々入れてみて反応を探る。
ロジックはほぼほぼ完成


とりあえずクライアントの環境整ったのでロジックとのつなぎを考える。
終われば4目並べに移りたいところ。いつまでかかるやら。。。

# クライアントビルド方法

以下のコマンドでreflesh
```
sbt ~fastOptJS
```

以下にアクセス

http://localhost:12345/target/scala-2.11/classes/index.html

workbench導入してうまくliveLeloadはできるようになった

ビルド周りはこちらを参考にしまくった

http://www.lihaoyi.com/hands-on-scala-js/

# 今週の目標

五目並べまで進まないといけない。
３目並べを作成。そこから一旦五目並べへとつなげる

https://github.com/playframework/twirl
を使用してtemplateを用意する

# 参考URL

[ロジック](http://postd.cc/tic-tac-toe-understanding-the-minimax-algorithm/)

[TickTacToe](https://codepen.io/ziga-miklic/pen/Fagmh?editors=1000)
