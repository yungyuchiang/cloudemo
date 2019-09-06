<template>
  <div class="div-root" v-if="user">
    <div >{{user.name}}</div>
    <div >{{user.age}}</div>
    <div >{{user.phone}}</div>
  </div>
</template>
<script>
export default {
  props: ['user']
}
</script>
<style scoped>
.div-root {
  background: rebeccapurple;
}
</style>
