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

create or alter procedure createUser
    @username nvarchar(50),
    @passwordHash nvarchar(255),
    @role nvarchar(20),
    @id int output
as
begin
    set nocount on;

    insert into [User](username, password_hash, role)
    values (@username, @passwordHash, @role);

    set @id = scope_identity();
end
go

create or alter procedure selectUserByUsername
    @username nvarchar(50)
as
begin
    set nocount on;

    select id, username, password_hash, role
    from [User]
    where username = @username;
end
go
