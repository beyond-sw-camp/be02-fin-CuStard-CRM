<template>
  <div class="qna-article">
    <!-- 비밀번호가 입력되지 않았을 때 표시할 부분 -->
    <div v-if="!passwordEntered">
      <h2>게시글 비밀번호 입력</h2>
      <form @submit.prevent="submitPassword">
        <input type="password" v-model="password" placeholder="비밀번호를 입력하세요">
        <button type="submit">확인</button>
      </form>
    </div>

    <!-- 비밀번호 입력 후 표시할 부분 -->
    <div v-if="passwordEntered">
      <div class="container">
        <div class="title">{{ qna?.title || '제목' }}</div><hr>
        <ul class="status">
          <li>문의 유형: <span>{{ qna?.category || ' '}}</span></li>
          <li>상태:<span class="qnaAnswer"> {{ qna.answerContent !== null ? '답변 완료' : '답변 대기' }}</span></li>
        </ul>
        <hr>
        <div class="question">{{ qna?.qnaContent || '내용' }}</div>
      </div>
      <!-- 답변이 있으면 답변을 보여주고, 없으면 '답변내용이 없습니다' 를 표시 -->
      <div class="answercontainer">{{ qna?.answerContent || '작성된 답변이 없습니다.' }}</div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
// let backend = "http://localhost:8080";
let backend = "http://192.168.0.31:80/api";

export default {
  data() {
    return {
      password: '',
      passwordEntered: false,
      qna: null
    };
  },
  methods: {
    submitPassword() {
      // 비밀번호 제출 메소드 내에서 axios로 POST 요청을 보냅니다.
      axios.post(`${backend}/qna/read/${this.$route.params.idx}`, { qnaPwd: this.password })
          .then(response => {
            if (response.data.code === 1000) {
              this.qna = response.data.result; // 서버로부터 받은 데이터를 qna 객체에 할당합니다.
              this.passwordEntered = true; // 비밀번호가 확인되면 passwordEntered를 true로 설정합니다.
            } else {
              alert("비밀번호가 올바르지 않습니다."); // 오류 메시지를 표시합니다.
            }
          })
          .catch(error => {
            console.error("에러 발생:", error); // 콘솔에 에러를 표시합니다.
          });
    }
  },
  mounted() {
    this.passwordEntered = false; // 컴포넌트가 마운트되면 비밀번호 입력 상태를 false로 리셋합니다.
  }
};
</script>

<style scoped>
.title{
  font-size: 25px;
}

.qna-article {
  max-width: 800px;
  margin: 70px auto;
  padding-top: 10px; /* 위쪽 여백 */
  padding-bottom: 160px; /* 아래쪽 여백 */
}

input[type="password"] {
  padding: 10px;
  margin-bottom: 20px;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 100%;
}

button[type="submit"] {
  background-color: #FFFFFF;
  color: black;
  border: 1px solid black;
  padding: 10px;
  cursor: pointer;
  transition: background-color 0.5s;
  margin-bottom: 20px;
  width: 30%;
  box-sizing: border-box;
  display: block;
  margin-left: auto;
  margin-right: auto;
  margin-top: 10px;
  border-radius: 10px;

}

button[type="submit"]:hover {
  background-color: black;

  color: #FFFFFF;
}

.article-container {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
  background-color: #fff;
}

.article-header {
  margin-bottom: 20px;
}

.article-header h2 {
  font-size: 24px;
  margin-bottom: 10px;
}

.article-content p {
  font-size: 16px;
  line-height: 1.6;
}

.container, .answercontainer{
  max-width: 600px;
  margin: 50px auto;
  background: #fff;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.answercontainer{
  margin-top: 50px;
}

.question {
  font-size: 16px;
  color: #555;
  margin-top: 20px;
  font-weight: normal;
}
.status{
  display: flex;
  padding: 0;
}
.status>li{
  width: 30%;
  font-size: 13px;
  list-style: none;
  font-weight: normal;

}
.status>li>span{
  width: 30%;
  ;
  font-weight: bold;
  margin-left: 5px;
}
.qnaAnswer{
  width: 30%;
  color: #00ab03;
  font-weight: bold;
  margin-left: 5px;
}

</style>
