/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  Ardian
 * Created: Jan 26, 2026
 */

-- 01_database_and_tables.sql
IF DB_ID('MOVIES') IS NULL
BEGIN
    CREATE DATABASE MOVIES;
END
GO

USE MOVIES;
GO

IF OBJECT_ID('dbo.Movie', 'U') IS NULL
BEGIN
    CREATE TABLE dbo.Movie (
        IDMovie INT IDENTITY(1,1) PRIMARY KEY,
        Title NVARCHAR(300) NOT NULL,
        Link NVARCHAR(1000) NULL,
        Description NVARCHAR(MAX) NULL,
        PicturePath NVARCHAR(1000) NULL,
        PublishedDate DATETIME2 NOT NULL
    );
END
GO

