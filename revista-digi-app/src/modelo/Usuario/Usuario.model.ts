export class Usuario {
  private _usuario: String;
  private _contra: String;
  private _rol: String;

  constructor(usuario: String, contra: String, rol: String) {
    this._usuario = usuario;
    this._contra = contra;
    this._rol = rol;
  }

  get usuario() {
    return this._usuario;
  }

  get contra() {
    return this._contra;
  }

  set usuario(usuario: String) {
    this._usuario = usuario;
  }

  set contra(contra: String) {
    this._contra = contra;
  }

  set rol(rol: String) {
    this._rol = rol;
  }

  get rol() {
    return this._rol ;
  }



}
