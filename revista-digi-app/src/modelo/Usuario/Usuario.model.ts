export class Usuario {
  usuario: String;
  password: String;
  tipoCuenta: number;

  constructor(usuario: String, password: String, tipoCuenta: number) {
    this.usuario = usuario;
    this.password = password;
    this.tipoCuenta = tipoCuenta;
  }

  get getUsuario() {
    return this.usuario;
  }

  get getPassword() {
    return this.password;
  }

  set setUsuario(usuario: String) {
    this.usuario = usuario;
  }

  set setPassword(contra: String) {
    this.password = contra;
  }

  set setTipoCuenta(rol: number) {
    this.tipoCuenta = rol;
  }

  get getTipoCuenta() {
    return this.tipoCuenta ;
  }



}
