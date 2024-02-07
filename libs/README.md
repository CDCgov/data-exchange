# Info

This folder holds multiple projects - each being a reusable libraries for other DEX projects to be used.

Each library should have full CI/CD to propagate its package deliverables to a public repository.
Today, the idea is to push them to github packages, since this is a public facing repo.

Since different projects uses different languages, the first folder level under each project should be 
the language in which it's implemented in. Some libraries will have to be implemented in multiple languages.

**TBD: How to maintain consistency among those implementations**

# Current Libraries:

## Metadata

This library defines the dex-wide metadata schema to be used across all other DEX components.
