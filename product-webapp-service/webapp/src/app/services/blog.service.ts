// src/app/blog.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Blog } from '../blog.model';

@Injectable({
  providedIn: 'root'
})
export class BlogService {
  private apiUrl = 'http://localhost:9999/api/blog'; 
  // Spring Boot API URL

  constructor(private http: HttpClient) {}

  getAllBlogs(): Observable<Blog[]> {
    return this.http.get<Blog[]>(`${this.apiUrl}/viewall`);
  }

  getBlogById(blogId: number): Observable<Blog> {
    return this.http.get<Blog>(`${this.apiUrl}/${blogId}`);
  }

  addBlog(blog: Blog): Observable<any> {
    return this.http.post(`${this.apiUrl}/addblog`, blog);
  }

  updateBlog(blog: Blog): Observable<any> {
    return this.http.put(`${this.apiUrl}/update`, blog);
  }

  deleteBlogById(blogId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${blogId}`);
  }
}
