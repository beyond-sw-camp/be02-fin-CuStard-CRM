<template>
  <div id="articlelist">
    <main>
      <form @submit.prevent="submitForm">
        <label for="title">제목:</label>
        <input type="text" id="title" v-model="title" required>

        <label for="content">내용:</label>
        <textarea id="content" v-model="content" rows="4" required></textarea>

        <label for="password">비밀번호:</label>
        <input type="password" id="password" v-model="password" required>

        <button type="submit">문의하기</button>
      </form>
    </main>
  </div>
</template>

<script>
import axios from 'axios';

let backend = "http://192.168.0.53:80/api";
// let backend = "http://localhost:8080";

export default {
  data() {
    return {
      title: '',
      content: '',
      password: '',
      articles: []
    };
  },
  methods: {
    submitForm() {
      const accessToken = localStorage.getItem('accessToken');
      console.log(accessToken)
      axios.post(backend + '/qna/register', {
            title: this.title,
            qnaContent: this.content,
            qnaPwd: this.password   },{
            headers: { 'Authorization': `${accessToken}` },
          }
      )
          .then((response) => {
            console.log("데이터 전송 성공:", response.data);
            this.title = '';
            this.content = '';
            this.password = '';
            alert("문의를 성공적으로 작성했습니다");
            this.$router.push("/qna/list");
          })

          .catch((error) => {
            console.error("데이터 전송 실패:", error);
          });
    }
  }
};
</script>

<style scoped>
#articlelist {
  margin-top: 60px;
}

form {
  margin-top: 10px;
}

input[type="text"] {
  margin-bottom: 20px;
}

main {
    max-width: 600px;
    margin: 20px auto;
    margin-bottom: 80px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
}

form {
    display: flex;
    flex-direction: column;
    align-items: flex-start; /* 왼쪽 정렬 */

}

label {
    margin-bottom: 10px;
    width: 100px; /* 레이블 너비 조정 */

}

input, textarea {
    margin-bottom: 15px; /* 조정된 부분 */
    padding: 8px;
    width: 100%; /* 입력 칸 너비 조정 */
    box-sizing: border-box; /* 너비에 padding, border 포함 */
}

button {
    background-color: #ffbe0e;
    color: #fff;
    border: none;
    padding: 10px;
    cursor: pointer;
    transition: background-color 0.3s;
    margin-bottom: 20px;
    width: 100%; /* 버튼의 너비를 100%로 설정하여 왼쪽으로 정렬되도록 함 */
    box-sizing: border-box; /* 너비에 padding, border 포함 */
}

button:hover {
    background-color: #99154E;
}
</style>
