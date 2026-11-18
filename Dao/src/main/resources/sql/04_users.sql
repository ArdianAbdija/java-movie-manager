/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Ardian
 * Created: Jan 27, 2026
 */

 use movies;
go

create table [User] (
    id int identity(1,1) primary key,
    username nvarchar(50) not null unique,
    password_hash nvarchar(255) not null,
    role nvarchar(20) not null,
    created_at datetime2 not null default sysdatetime()
);
go
