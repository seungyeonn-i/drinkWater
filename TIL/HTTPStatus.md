# http.status

### - Email 중복 확인 409 Conflict

403과 비교할만한 상태코드는 409 Conflict 입니다.

```java
/**
	 * {@code 409 Conflict}.
	 * @see <a href="https://tools.ietf.org/html/rfc7231#section-6.5.8">HTTP/1.1: Semantics and Content, section 6.5.8</a>
	 */
	CONFLICT(409, "Conflict")
```

[6.5.8](https://tools.ietf.org/html/rfc7231#section-6.5.8)

. 409 Conflict

---

409 Conflick

---

409 Conflict 코드는 리소스가 충돌이 발생하였고 사용자가 이를 반영할 수 있을 때 발생하는 상태 코드입니다. 아이디 중복 체크에서는 사용자가 충돌되지 않는 다른 아이디를 사용하여 충돌을 해결해야 하기 때문에 이 상태 코드도 적합하다고 생각하였습니다. 409 코드에 대하여 조사해보니 다른 웹 사이트에서도 ID 중복 발생 시 409 코드를 많이 쓴다고 현업자 분들의 의견이 달려있는 것을 확인하였습니다.

423 Locked는 ID 중복체크와는 관계가 없는 상태코드입니다. 사용할 수 없습니다.
403 Forbidden은 Id 중복체크 보다는 데이터의 유효성이나 인가 실패에 사용하는 코드입니다. Id 중복체크에 사용이 가능할 것이라고 생각하지만 ID가 중복되었다는 상태에 대한 명확한 처리는 아니라고 생각합니다.
409 Conflict는 리소스의 충돌을 의미하는 상태코드입니다. ID 중복이라는 것은 결국 ID라는 PK 자원을 점유한 것에 대한 충돌이기 때문에 이 상태코드가 가장 적합하다고 생각하여 409 상태코드를 반영하기로 했습니다.