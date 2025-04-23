const base = {
    get() {
        return {
            url : "http://localhost:8080/kejiyinhangyewuguanli/",
            name: "kejiyinhangyewuguanli",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/kejiyinhangyewuguanli/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "科技银行业务管理系统"
        } 
    }
}
export default base
