## 
## 使用言語、フレームワーク
### 言語
- Java (MyBatis用エンティティ)
- kotlin (メインコード)

### フレームワーク
#### メイン
- Spring Framework
   L Actuator
   L OAuth2
   L JDBC
   L Web
   L Rest
   L Security
- Thymeleaf
- MyBatis
- SpringDoc
- MySQL
- Auth0
#### テスト
- JUnit
- SpringBoot-Test
- AssertJ

# 実装内容
- ユーザー登録と権限設定
- セキュリティー設定
- Bearer認証で認証できたユーザーのみアクセス可能なRest API(使用アルゴリズム: HMAC256)

## ルートマップのソード
[ユーザーのルートコントローラー](/src/main/kotlin/com/github/peco2282/webapp/UserRouteController.kt)
- ユーザー登録, 更新, 他ユーザーの閲覧
[投票関係のルートコントローラー](/src/main/kotlin/com/github/peco2282/webapp/VoteRouteController.kt) (作成途中)

[APIのルートコントローラー](/src/main/kotlin/com/github/peco2282/webapp/api/RestCotroller.kt)

## TODO
- APIのPOST, DELETE通信対応
- ユーザー更新フォーム、token作成