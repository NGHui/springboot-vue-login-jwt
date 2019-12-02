<template>
    <h1>Success</h1>
</template>

<script>
    export default {
        name: "Success",
        methods:{
            getList(){
                this.$http.get('/api/list')
                    .then((res)=>{
                        console.log(res.data)
                    })
            }
        },
        created() {
            this.getList()
        }
    }
</script>

<style scoped>

</style>
