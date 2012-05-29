
-- --------------------------------------------------
-- Entity Designer DDL Script for SQL Server 2005, 2008, and Azure
-- --------------------------------------------------
-- Date Created: 04/25/2012 00:13:29
-- Generated from EDMX file: W:\Repositories\MDSD\modeling_1_2012\trunk\hu.bme.mit.inf.gs.AppStore.AppRepository\AppRepository.edmx
-- --------------------------------------------------

SET QUOTED_IDENTIFIER OFF;
GO
USE [AppRepository];
GO
IF SCHEMA_ID(N'dbo') IS NULL EXECUTE(N'CREATE SCHEMA [dbo]');
GO

-- --------------------------------------------------
-- Dropping existing FOREIGN KEY constraints
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[FK_ApplicationEntityApplicationVersionEntitylastAcceptedVersion]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ApplicationVersionEntitySet] DROP CONSTRAINT [FK_ApplicationEntityApplicationVersionEntitylastAcceptedVersion];
GO
IF OBJECT_ID(N'[dbo].[FK_ApplicationEntityApplicationVersionEntitylastCommitedVersion]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ApplicationVersionEntitySet] DROP CONSTRAINT [FK_ApplicationEntityApplicationVersionEntitylastCommitedVersion];
GO
IF OBJECT_ID(N'[dbo].[FK_ApplicationEntityApplicationMetadataEntitylastAcceptedMetadata]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ApplicationMetadataEntitySet] DROP CONSTRAINT [FK_ApplicationEntityApplicationMetadataEntitylastAcceptedMetadata];
GO
IF OBJECT_ID(N'[dbo].[FK_ApplicationEntityApplicationMetadataEntitylastCommitedMetadata]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ApplicationMetadataEntitySet] DROP CONSTRAINT [FK_ApplicationEntityApplicationMetadataEntitylastCommitedMetadata];
GO
IF OBJECT_ID(N'[dbo].[FK_ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationAccountEntity]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ApplicationAccountEntityApplicationEntityboughtApplicationsCollection] DROP CONSTRAINT [FK_ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationAccountEntity];
GO
IF OBJECT_ID(N'[dbo].[FK_ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ApplicationAccountEntityApplicationEntityboughtApplicationsCollection] DROP CONSTRAINT [FK_ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity];
GO
IF OBJECT_ID(N'[dbo].[FK_ApplicationMetadataEntityCategoryEntitycategoriesCollection_ApplicationMetadataEntity]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ApplicationMetadataEntityCategoryEntitycategoriesCollection] DROP CONSTRAINT [FK_ApplicationMetadataEntityCategoryEntitycategoriesCollection_ApplicationMetadataEntity];
GO
IF OBJECT_ID(N'[dbo].[FK_ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[ApplicationMetadataEntityCategoryEntitycategoriesCollection] DROP CONSTRAINT [FK_ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity];
GO
IF OBJECT_ID(N'[dbo].[FK_ApplicationVersionEntityStringfilePathsCollection]', 'F') IS NOT NULL
    ALTER TABLE [dbo].[StringfilePathsEntitySet] DROP CONSTRAINT [FK_ApplicationVersionEntityStringfilePathsCollection];
GO

-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[ApplicationEntitySet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ApplicationEntitySet];
GO
IF OBJECT_ID(N'[dbo].[ApplicationAccountEntitySet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ApplicationAccountEntitySet];
GO
IF OBJECT_ID(N'[dbo].[ApplicationMetadataEntitySet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ApplicationMetadataEntitySet];
GO
IF OBJECT_ID(N'[dbo].[ApplicationVersionEntitySet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ApplicationVersionEntitySet];
GO
IF OBJECT_ID(N'[dbo].[CategoryEntitySet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[CategoryEntitySet];
GO
IF OBJECT_ID(N'[dbo].[StringfilePathsEntitySet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[StringfilePathsEntitySet];
GO
IF OBJECT_ID(N'[dbo].[ApplicationAccountEntityApplicationEntityboughtApplicationsCollection]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ApplicationAccountEntityApplicationEntityboughtApplicationsCollection];
GO
IF OBJECT_ID(N'[dbo].[ApplicationMetadataEntityCategoryEntitycategoriesCollection]', 'U') IS NOT NULL
    DROP TABLE [dbo].[ApplicationMetadataEntityCategoryEntitycategoriesCollection];
GO

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

-- Creating table 'ApplicationEntitySet'
CREATE TABLE [dbo].[ApplicationEntitySet] (
    [applicationID] int IDENTITY(1,1) NOT NULL,
    [creatorName] nvarchar(max)  NULL
);
GO

-- Creating table 'ApplicationAccountEntitySet'
CREATE TABLE [dbo].[ApplicationAccountEntitySet] (
    [accountUserName] nvarchar(max)  NULL,
    [applicationAccountID] int IDENTITY(1,1) NOT NULL
);
GO

-- Creating table 'ApplicationMetadataEntitySet'
CREATE TABLE [dbo].[ApplicationMetadataEntitySet] (
    [applicationMetadataID] int IDENTITY(1,1) NOT NULL,
    [timestamp] datetime  NULL,
    [name] nvarchar(max)  NULL,
    [price] float  NULL,
    [description] nvarchar(max)  NULL,
    [acceptanceResult] int  NULL,
    [ageRestriction] int  NULL,
    [ApplicationEntityApplicationMetadataEntitylastAcceptedMetadata_ApplicationMetadataEntity_applicationID] int  NULL,
    [ApplicationEntityApplicationMetadataEntitylastCommitedMetadata_ApplicationMetadataEntity_applicationID] int  NULL
);
GO

-- Creating table 'ApplicationVersionEntitySet'
CREATE TABLE [dbo].[ApplicationVersionEntitySet] (
    [applicationVersionID] int IDENTITY(1,1) NOT NULL,
    [timestamp] datetime  NULL,
    [versionString] nvarchar(max)  NULL,
    [acceptanceResult] int  NULL,
    [ApplicationEntityApplicationVersionEntitylastAcceptedVersion_ApplicationVersionEntity_applicationID] int  NULL,
    [ApplicationEntityApplicationVersionEntitylastCommitedVersion_ApplicationVersionEntity_applicationID] int  NULL
);
GO

-- Creating table 'CategoryEntitySet'
CREATE TABLE [dbo].[CategoryEntitySet] (
    [categoryName] nvarchar(255)  NOT NULL
);
GO

-- Creating table 'StringfilePathsEntitySet'
CREATE TABLE [dbo].[StringfilePathsEntitySet] (
    [Id] int IDENTITY(1,1) NOT NULL,
    [filePaths] nvarchar(max)  NULL,
    [ApplicationVersionEntityStringfilePathsCollection_StringfilePathsEntity_applicationVersionID] int  NOT NULL
);
GO

-- Creating table 'ApplicationAccountEntityApplicationEntityboughtApplicationsCollection'
CREATE TABLE [dbo].[ApplicationAccountEntityApplicationEntityboughtApplicationsCollection] (
    [ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity_applicationAccountID] int  NOT NULL,
    [boughtApplications_applicationID] int  NOT NULL
);
GO

-- Creating table 'ApplicationMetadataEntityCategoryEntitycategoriesCollection'
CREATE TABLE [dbo].[ApplicationMetadataEntityCategoryEntitycategoriesCollection] (
    [ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity_applicationMetadataID] int  NOT NULL,
    [categories_categoryName] nvarchar(255)  NOT NULL
);
GO

-- --------------------------------------------------
-- Creating all PRIMARY KEY constraints
-- --------------------------------------------------

-- Creating primary key on [applicationID] in table 'ApplicationEntitySet'
ALTER TABLE [dbo].[ApplicationEntitySet]
ADD CONSTRAINT [PK_ApplicationEntitySet]
    PRIMARY KEY CLUSTERED ([applicationID] ASC);
GO

-- Creating primary key on [applicationAccountID] in table 'ApplicationAccountEntitySet'
ALTER TABLE [dbo].[ApplicationAccountEntitySet]
ADD CONSTRAINT [PK_ApplicationAccountEntitySet]
    PRIMARY KEY CLUSTERED ([applicationAccountID] ASC);
GO

-- Creating primary key on [applicationMetadataID] in table 'ApplicationMetadataEntitySet'
ALTER TABLE [dbo].[ApplicationMetadataEntitySet]
ADD CONSTRAINT [PK_ApplicationMetadataEntitySet]
    PRIMARY KEY CLUSTERED ([applicationMetadataID] ASC);
GO

-- Creating primary key on [applicationVersionID] in table 'ApplicationVersionEntitySet'
ALTER TABLE [dbo].[ApplicationVersionEntitySet]
ADD CONSTRAINT [PK_ApplicationVersionEntitySet]
    PRIMARY KEY CLUSTERED ([applicationVersionID] ASC);
GO

-- Creating primary key on [categoryName] in table 'CategoryEntitySet'
ALTER TABLE [dbo].[CategoryEntitySet]
ADD CONSTRAINT [PK_CategoryEntitySet]
    PRIMARY KEY CLUSTERED ([categoryName] ASC);
GO

-- Creating primary key on [Id] in table 'StringfilePathsEntitySet'
ALTER TABLE [dbo].[StringfilePathsEntitySet]
ADD CONSTRAINT [PK_StringfilePathsEntitySet]
    PRIMARY KEY CLUSTERED ([Id] ASC);
GO

-- Creating primary key on [ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity_applicationAccountID], [boughtApplications_applicationID] in table 'ApplicationAccountEntityApplicationEntityboughtApplicationsCollection'
ALTER TABLE [dbo].[ApplicationAccountEntityApplicationEntityboughtApplicationsCollection]
ADD CONSTRAINT [PK_ApplicationAccountEntityApplicationEntityboughtApplicationsCollection]
    PRIMARY KEY NONCLUSTERED ([ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity_applicationAccountID], [boughtApplications_applicationID] ASC);
GO

-- Creating primary key on [ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity_applicationMetadataID], [categories_categoryName] in table 'ApplicationMetadataEntityCategoryEntitycategoriesCollection'
ALTER TABLE [dbo].[ApplicationMetadataEntityCategoryEntitycategoriesCollection]
ADD CONSTRAINT [PK_ApplicationMetadataEntityCategoryEntitycategoriesCollection]
    PRIMARY KEY NONCLUSTERED ([ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity_applicationMetadataID], [categories_categoryName] ASC);
GO

-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- Creating foreign key on [ApplicationEntityApplicationVersionEntitylastAcceptedVersion_ApplicationVersionEntity_applicationID] in table 'ApplicationVersionEntitySet'
ALTER TABLE [dbo].[ApplicationVersionEntitySet]
ADD CONSTRAINT [FK_ApplicationEntityApplicationVersionEntitylastAcceptedVersion]
    FOREIGN KEY ([ApplicationEntityApplicationVersionEntitylastAcceptedVersion_ApplicationVersionEntity_applicationID])
    REFERENCES [dbo].[ApplicationEntitySet]
        ([applicationID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ApplicationEntityApplicationVersionEntitylastAcceptedVersion'
CREATE INDEX [IX_FK_ApplicationEntityApplicationVersionEntitylastAcceptedVersion]
ON [dbo].[ApplicationVersionEntitySet]
    ([ApplicationEntityApplicationVersionEntitylastAcceptedVersion_ApplicationVersionEntity_applicationID]);
GO

-- Creating foreign key on [ApplicationEntityApplicationVersionEntitylastCommitedVersion_ApplicationVersionEntity_applicationID] in table 'ApplicationVersionEntitySet'
ALTER TABLE [dbo].[ApplicationVersionEntitySet]
ADD CONSTRAINT [FK_ApplicationEntityApplicationVersionEntitylastCommitedVersion]
    FOREIGN KEY ([ApplicationEntityApplicationVersionEntitylastCommitedVersion_ApplicationVersionEntity_applicationID])
    REFERENCES [dbo].[ApplicationEntitySet]
        ([applicationID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ApplicationEntityApplicationVersionEntitylastCommitedVersion'
CREATE INDEX [IX_FK_ApplicationEntityApplicationVersionEntitylastCommitedVersion]
ON [dbo].[ApplicationVersionEntitySet]
    ([ApplicationEntityApplicationVersionEntitylastCommitedVersion_ApplicationVersionEntity_applicationID]);
GO

-- Creating foreign key on [ApplicationEntityApplicationMetadataEntitylastAcceptedMetadata_ApplicationMetadataEntity_applicationID] in table 'ApplicationMetadataEntitySet'
ALTER TABLE [dbo].[ApplicationMetadataEntitySet]
ADD CONSTRAINT [FK_ApplicationEntityApplicationMetadataEntitylastAcceptedMetadata]
    FOREIGN KEY ([ApplicationEntityApplicationMetadataEntitylastAcceptedMetadata_ApplicationMetadataEntity_applicationID])
    REFERENCES [dbo].[ApplicationEntitySet]
        ([applicationID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ApplicationEntityApplicationMetadataEntitylastAcceptedMetadata'
CREATE INDEX [IX_FK_ApplicationEntityApplicationMetadataEntitylastAcceptedMetadata]
ON [dbo].[ApplicationMetadataEntitySet]
    ([ApplicationEntityApplicationMetadataEntitylastAcceptedMetadata_ApplicationMetadataEntity_applicationID]);
GO

-- Creating foreign key on [ApplicationEntityApplicationMetadataEntitylastCommitedMetadata_ApplicationMetadataEntity_applicationID] in table 'ApplicationMetadataEntitySet'
ALTER TABLE [dbo].[ApplicationMetadataEntitySet]
ADD CONSTRAINT [FK_ApplicationEntityApplicationMetadataEntitylastCommitedMetadata]
    FOREIGN KEY ([ApplicationEntityApplicationMetadataEntitylastCommitedMetadata_ApplicationMetadataEntity_applicationID])
    REFERENCES [dbo].[ApplicationEntitySet]
        ([applicationID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ApplicationEntityApplicationMetadataEntitylastCommitedMetadata'
CREATE INDEX [IX_FK_ApplicationEntityApplicationMetadataEntitylastCommitedMetadata]
ON [dbo].[ApplicationMetadataEntitySet]
    ([ApplicationEntityApplicationMetadataEntitylastCommitedMetadata_ApplicationMetadataEntity_applicationID]);
GO

-- Creating foreign key on [ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity_applicationAccountID] in table 'ApplicationAccountEntityApplicationEntityboughtApplicationsCollection'
ALTER TABLE [dbo].[ApplicationAccountEntityApplicationEntityboughtApplicationsCollection]
ADD CONSTRAINT [FK_ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationAccountEntity]
    FOREIGN KEY ([ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity_applicationAccountID])
    REFERENCES [dbo].[ApplicationAccountEntitySet]
        ([applicationAccountID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating foreign key on [boughtApplications_applicationID] in table 'ApplicationAccountEntityApplicationEntityboughtApplicationsCollection'
ALTER TABLE [dbo].[ApplicationAccountEntityApplicationEntityboughtApplicationsCollection]
ADD CONSTRAINT [FK_ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity]
    FOREIGN KEY ([boughtApplications_applicationID])
    REFERENCES [dbo].[ApplicationEntitySet]
        ([applicationID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity'
CREATE INDEX [IX_FK_ApplicationAccountEntityApplicationEntityboughtApplicationsCollection_ApplicationEntity]
ON [dbo].[ApplicationAccountEntityApplicationEntityboughtApplicationsCollection]
    ([boughtApplications_applicationID]);
GO

-- Creating foreign key on [ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity_applicationMetadataID] in table 'ApplicationMetadataEntityCategoryEntitycategoriesCollection'
ALTER TABLE [dbo].[ApplicationMetadataEntityCategoryEntitycategoriesCollection]
ADD CONSTRAINT [FK_ApplicationMetadataEntityCategoryEntitycategoriesCollection_ApplicationMetadataEntity]
    FOREIGN KEY ([ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity_applicationMetadataID])
    REFERENCES [dbo].[ApplicationMetadataEntitySet]
        ([applicationMetadataID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;
GO

-- Creating foreign key on [categories_categoryName] in table 'ApplicationMetadataEntityCategoryEntitycategoriesCollection'
ALTER TABLE [dbo].[ApplicationMetadataEntityCategoryEntitycategoriesCollection]
ADD CONSTRAINT [FK_ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity]
    FOREIGN KEY ([categories_categoryName])
    REFERENCES [dbo].[CategoryEntitySet]
        ([categoryName])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity'
CREATE INDEX [IX_FK_ApplicationMetadataEntityCategoryEntitycategoriesCollection_CategoryEntity]
ON [dbo].[ApplicationMetadataEntityCategoryEntitycategoriesCollection]
    ([categories_categoryName]);
GO

-- Creating foreign key on [ApplicationVersionEntityStringfilePathsCollection_StringfilePathsEntity_applicationVersionID] in table 'StringfilePathsEntitySet'
ALTER TABLE [dbo].[StringfilePathsEntitySet]
ADD CONSTRAINT [FK_ApplicationVersionEntityStringfilePathsCollection]
    FOREIGN KEY ([ApplicationVersionEntityStringfilePathsCollection_StringfilePathsEntity_applicationVersionID])
    REFERENCES [dbo].[ApplicationVersionEntitySet]
        ([applicationVersionID])
    ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Creating non-clustered index for FOREIGN KEY 'FK_ApplicationVersionEntityStringfilePathsCollection'
CREATE INDEX [IX_FK_ApplicationVersionEntityStringfilePathsCollection]
ON [dbo].[StringfilePathsEntitySet]
    ([ApplicationVersionEntityStringfilePathsCollection_StringfilePathsEntity_applicationVersionID]);
GO

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------