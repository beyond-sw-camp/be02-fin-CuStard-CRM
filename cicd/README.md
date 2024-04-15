## CI / CD

## 👊 목표
* 소프트웨어 개발 및 배포 프로세스를 자동화하고 향상시켜 개발 및 배포 과정을 더욱 효율적으로 만드는 것


## ❗ CI / CD 구성의 필요성

* CI CD 환경이 구성되기 전에는 Pull Request를 할 때마다 코드가 제대로 동작하는지 확인해야 했었습니다.
* 이 작업을 반복하는 시간적 자원, 인적 자원보다 한 번 환경 구축을 통해 앞으로의 작업을 자동화 하는 것이 더 득이 될 것이라고 판단하여 CI CD 파이프라인을 구축하였습니다.



## 📌 기술 스택

<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white">
<img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white">
<img src="https://img.shields.io/badge/jest-C21325?style=for-the-badge&logo=jest&logoColor=white">
<img src="https://img.shields.io/badge/junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white">
<img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white">
<img src="https://img.shields.io/badge/prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white">
<img src="https://img.shields.io/badge/grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white">
<img src="https://img.shields.io/badge/apachejmeter-D22128?style=for-the-badge&logo=apachejmeter&logoColor=white">



## 🧱 아키텍처

<img src=https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/58664027/f5606201-1f2b-4324-9835-238caecea25c>

### 📆 배포시나리오

1. 개발자가 소스 코드를 형상 관리 시스템에 저장한다.
2. 저장을 하게 되면 형상 관리 시스템은 jenkins에게 webhook을 보내게 된다.
3. jenkins는 webhook을 받게 되면 형상 관리 시스템으로부터 가장 최근에 저장된 코드를 받아온다.
4. 받아온 코드를 테스트하여 성공 또는 실패를 slack 알림으로 보낸다.
5. 테스트가 끝난 코드를 하나의 패키지로 만든다.
6. 테스트를 완료한 패키지를 이미지로 만들고 도커 허브에 푸시한다.
7. 푸시한 이미지를 k8s에 업데이트한다.


### Custard 팀의 CI CD관련 더 자세한 설명을 보러가기
<a href="https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/wiki/CI---CD-%ED%8C%8C%EC%9D%B4%ED%94%84%EB%9D%BC%EC%9D%B8">바로가기
</a>