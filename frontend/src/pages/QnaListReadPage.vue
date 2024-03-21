<template>
  <div class="qna-article">
    <div v-if="!passwordEntered">
      <h2>게시글 비밀번호 입력</h2>
      <form @submit.prevent="submitPassword">
        <input type="password" v-model="password" placeholder="비밀번호를 입력하세요">
        <button type="submit">확인</button>
      </form>
    </div>
    <div v-else>
      <div v-if="qna && qna.answerContent" class="article-container">
        <div class="article-header">
          <h2>{{ qna.title }}</h2>
        </div>
        <div class="article-content">
          <p>{{ qna.qnaContent }}</p>
          <h3>답변:</h3>
          <p>{{ qna.answerContent }}</p>
        </div>
      </div>
      <div v-else class="article-container">
        <div class="article-header">
          <h2>{{ qna.title }}</h2>
        </div>
        <div class="article-content">
          <p>{{ qna.qnaContent }}</p>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import axios from 'axios';
let backend = "http://192.168.0.53:80/api";
// let backend = "http://localhost:8080";

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
      axios.post(backend + "/qna/read/"+ `${this.$route.params.idx}`, { qnaPwd: this.password })
          .then(response => {
            if (response.data.code === 1000) {
              this.qna = response.data.result;
              this.passwordEntered = true;
            } else {
              // 비밀번호가 틀렸을 때 처리
              alert("비밀번호가 올바르지 않습니다.");
            }
          })
          .catch(error => {
            console.error("에러 발생:", error);
          });
    }
  },
  mounted() {
    // 비밀번호 입력 페이지를 기본으로 표시
    this.passwordEntered = false;
  }
};
</script>

<style scoped>
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
  background-color: #ffbe0e;
  color: #fff;
  border: none;
  padding: 10px;
  cursor: pointer;
  transition: background-color 0.5s;
  margin-bottom: 20px;
  width: 100%;
  box-sizing: border-box;
}

button[type="submit"]:hover {
  background-color: #99154E;
}

.article-container {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 20px;
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
</style>
