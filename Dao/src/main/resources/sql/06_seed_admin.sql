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

declare @id int;

exec createUser
    @username = N'admin',
    @passwordHash = N'pbkdf2$65536$FBMRMmOBUnNhnQL04p99PA==$6q4FBHFsEfNpJAaZ4bfbXCWVV+XXDLdqju3D6ux29n4=',
    @role = N'admin',
    @id = @id output;

select @id as created_admin_id;
go


select id, username, role, created_at
from [User]
where username = N'admin';
