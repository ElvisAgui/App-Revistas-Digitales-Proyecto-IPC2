import { Usuario } from './../../modelo/Usuario/Usuario.model';
import { NvarServiceService } from './nvar-service.service';
import { Revista } from './../../modelo/Revista/revista';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FileService {
  editor!: String;
  readonly API_URL = 'http://localhost:8080/RevistasDigitales/UploadRevista';
  readonly Appi_Imag ='http://localhost:8080/RevistasDigitales/';
  readonly DOWNLOAD_FILE_URL = 'http://localhost:8080/RevistasDigitales/files/download-servlet';

  constructor(private httpClient: HttpClient, private navar: NvarServiceService) {}

  public fileUpload(fileToUpload: File): Observable<void> {
    const formData: FormData = new FormData();
    formData.append('datafile', fileToUpload, fileToUpload.name);
    return this.httpClient.put<void>(this.API_URL, formData);
  }

  public fileUploadEdicion(fileToUpload: File): Observable<void> {
    const formData: FormData = new FormData();
    formData.append('datafile', fileToUpload, fileToUpload.name);
    return this.httpClient.put<void>(this.API_URL+"?esEdicion=true", formData);
  }

  public crearRevista(revista: Revista): Observable<void>{
    return this.httpClient.post<void>(this.API_URL, revista); 
  }
  
  public getListaRevistas(): Observable<Revista[]>{
    this.editor = this.navar.usuario.usuario;
    return this.httpClient.get<Revista[]>(this.API_URL+ "?editor="+this.editor);
  }

  public getListaRevistasT(): Observable<Revista[]>{
    return this.httpClient.get<Revista[]>(this.API_URL);
  }

  public fileUploaImagePerfil(fileToUpload: File, usuario: String): Observable<void> {
    const formData: FormData = new FormData();
    formData.append('datafile', fileToUpload, fileToUpload.name);
    return this.httpClient.put<void>(this.Appi_Imag+"ControladorUsuario?esImage="+usuario, formData);
  }

  public downloadImage(usuario: Usuario): string {
    return this.Appi_Imag+"ControladorUsuario?usuario="+usuario.usuario;
  }
}