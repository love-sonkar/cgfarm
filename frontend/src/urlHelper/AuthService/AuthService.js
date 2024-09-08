class AuthService {
  constructor() {
    this.BASE_URL = import.meta.env.VITE_BASE_URL;
  }
  async createUsers() {
    try {
      let url = this.BASE_URL;
      return fetch(url, {
        method: "GET",
      })
        .then((data) => data.json())
        .then((data) => {
          if (data.error !== undefined) {
            console.log("err");
          }
          return data;
        });
    } catch (err) {
      console.log(err);
    }
  }
}

export default AuthService;
