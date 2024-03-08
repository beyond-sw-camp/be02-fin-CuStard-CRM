<template>
                                <footer>
                                    <ul class="bottom_util">
                                        <li>
                                            <a href="">TEAM 2</a>
                                        </li>
                                        <li>
                                            <a href="">CRM</a>
                                        </li>
                                        <li>
                                            <a href="" target="_blank">CuStard</a>
                                        </li>
                                    </ul>
                                    <div class="bottom_logo">
                                        <img src="../../public/img/logo.png" class="footlogo">
                                    </div>
                                    <p class="copy_info">
                                        <span>서울 동작구 보라매로 87 4층
                                        </span>
                                        <span>TEL : 02-000-0000</span>화이팅!</p>
                                    <p class="copy">HANHWA SW CAMP 2기</p>
                                </footer>
</template>

<script>
    export default {
        
    }
</script>

<style scoped>
html, button, input, select, textarea {
    font-family: "Noto Sans", "malgun gothic", AppleGothic, dotum, sans-serif;
    cursor: pointer;

    width: 1284px;
    /* border: 1px solid red; */
    margin: 0 auto;
}


/* footer */
footer {
    position: relative;
    clear: both;
    width: 100%;
    background: #c4c4c417;
    text-align: center;
    height: 200px;
}

footer .bottom_util {
    border-bottom: solid 1px #4646462b;;
    border-top: solid 1px #4646462b;;
    padding: 16px 0;
    display: flex;
    justify-content: center;
}
footer .bottom_util li {
    display: inline-block;
    margin: 3px 19px;
}
footer .bottom_ccm {
    position: absolute;
    text-align: right;
}

footer .bottom_ccm span {
    display: block;
    width: 98%;
    max-width: 1178px;
    margin: auto;
}
footer .copy_info {
    width: 90%;
    max-width: 650px;
    margin: auto;
    font-size: 12px;
    color: #6c6c6c;
    display: flex;
    justify-content: space-around;
    padding: 30px;
}
footer .copy_info span {
    display: inline-block;
    margin-right: 10px;
    padding-right: 10px;
}
footer .copy {
    padding-bottom: 15px;
    font-family: 'Nunito Sans', sans-serif;
    font-size: 12px;
    font-weight: 700;
    color: #6c6c6c;
    text-align: center;
    margin-top: 15px;
}
footer .bottom_util li a {
    color: #818181;
}
footer .bottom_util li a em {
    color: #FFF;
    font-weight: 400;
}

/* 임시 */
.content{
    height: 100px;
    height: 500px;
    background-color: wheat;
    text-align: center;
    }

.footlogo{
    width: 80px;
    height: 80px;
}

.bottom_logo{
    width: 82px;
    /* border: 1px solid red; */
    position: absolute;
    right: 75px;
    top: 95px
}
</style>