## 
## 使用言語、フレームワーク
### 言語
- Java (MyBatis用エンティティ)
- kotlin (メインコード)

### フレームワーク
#### メイン
- Spring Framework<br/>
   L Actuator<br/>
   L OAuth2<br/>
   L JDBC<br/>
   L Web<br/>
   L Rest<br/>
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
- ユーザー登録, 更新, 他ユーザーの閲覧<br/>

[投票関係のルートコントローラー](/src/main/kotlin/com/github/peco2282/webapp/VoteRouteController.kt) (作成途中)

[APIのルートコントローラー](/src/main/kotlin/com/github/peco2282/webapp/api/RestCotroller.kt)

## TODO
[TODO](/TODO.md)