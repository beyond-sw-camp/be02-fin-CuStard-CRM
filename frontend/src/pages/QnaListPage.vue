<template>
  <div class="qna-list">
    <ul>
      <li v-for="qna in qnas" :key="qna.idx" @click="goToArticle(qna.idx)">
        <div :class="['qna-item', qna.answerContent !== null ? 'qna-with-answer' : 'qna-without-answer']">
          <span>{{ qna.idx }}:</span>
          <span>{{ qna.title }}</span>
          <p>{{ qna.answerContent !== null ? '답변 완료' : '답변 대기' }}</p>
        </div>
      </li>
    </ul>
    <button @click="goToRegister" class="write-button">작성</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      qnas: []
    };
  },
  methods: {
    loadArticles() {
      axios.get("http://localhost:8080/qna/list")
          .then((response) => {
            this.qnas = response.data;
          })
          .catch((error) => {
            console.error("데이터 로드 실패:", error);
          });
    },
    goToArticle(idx) {
      this.$router.push({ path: `/qna/read/${idx}` });
    },
    goToRegister() {
      this.$router.push({ path: "/qna/write" });
    }
  },
  mounted() {
    this.loadArticles();
  }
};
</script>

<style scoped>
.qna-list {
  max-width: 600px;
  margin: 0 auto;
}

ul {
  padding: 0;
}

.qna-item {
  border: 1px solid #ccc;
  padding: 10px;
  margin-bottom: 10px;
  cursor: pointer;
}

.qna-item span {
  font-weight: bold;
}

.qna-item p {
  margin-top: 5px;
}

.qna-with-answer {
  background-color: #e6ffe6;
}

.qna-without-answer {
  background-color: #fff;
}

.write-button {
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.write-button:hover {
  background-color: #0056b3;
}
</style>