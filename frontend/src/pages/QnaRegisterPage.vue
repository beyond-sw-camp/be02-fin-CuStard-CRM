<template>
  <div id="articlelist">
    <form @submit.prevent="submitForm">
      <div>
        <label for="title">제목:</label>
        <input placeholder="제목을 입력해주세요" id="title" v-model="title" /><br /><br />
      </div>
      <div>
        <label for="content">내용:</label>
        <textarea placeholder="내용을 입력해주세요" id="content" v-model="content"></textarea><br /><br />
      </div>
      <div>
        <label for="password" id="passwword">비밀번호:</label>
        <input placeholder="비밀번호를 입력해주세요" id="password" type="password" v-model="password" />
      </div>

      <button type="submit">작성</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

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
      axios.post("http://localhost:8080/qna/register", {
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
  border: 1px solid #ccc;
  padding: 20px;
  max-width: 500px;
  margin: 0 auto;
  /* background: rgb(255, 169, 169); */
}
div{
  display: flex;
  align-items: center;
  margin-bottom: 50px;
}
div > :first-of-type{
  margin-right: 50px;
}
#passwword{
  margin-right: 18px;
}
form {
  display: flex;
  flex-direction: column;
}

label {
  margin-bottom: 5px;
}
input{
  border: none;
  border-bottom: 1.5px solid red;
}

textarea{
  resize: none;
}

input[type="text"],
textarea,
input[type="password"],
button {
  margin-bottom: 10px;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #007bff;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

button:active {
  background-color: #004280;
}
</style>