import { Revista } from './../../modelo/Revista/revista';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class FileService {
  readonly API_URL = 'http://localhost:8080/RevistasDigitales/UploadRevista';

  readonly DOWNLOAD_FILE_URL = 'http://localhost:8080/RevistasDigitales/files/download-servlet';

  constructor(private httpClient: HttpClient) {}

  public fileUpload(fileToUpload: File): Observable<void> {
    const formData: FormData = new FormData();
    formData.append('datafile', fileToUpload, fileToUpload.name);
    return this.httpClient.put<void>(this.API_URL, formData);
  }

  public crearRevista(revista: Revista): Observable<void>{
    return this.httpClient.post<void>(this.API_URL, revista); 
  }
}
 