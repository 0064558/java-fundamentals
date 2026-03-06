#!/bin/bash

# Script to consolidate multiple Java exercise repositories into a single java-fundamentals repository

# List of repositories to consolidate
repos=(
    "POO_bank"
    "array_quartos"
    "list_employee"
    "matrix"
    "composition1"
    "composition2"
    "composition3"
    "inheritance1"
    "inheritance4"
    "inheritance5"
    "inheritance6"
    "exceptions"
    "files"
    "interfaces1"
    "interfaces2"
)

# Target repository
target_repo="java-fundamentals"

# Clone the target repository
git clone https://github.com/0064558/$target_repo.git
cd $target_repo

# Iterate over each repository and consolidate using git subtree
for repo in "${repos[@]}"; do
    # Clone each repository to access the commit history
    git clone https://github.com/0064558/$repo.git
    cd $repo

    # Add the target as a remote
    git remote add target ../$target_repo

    # Push the history to the target using git subtree
    git subtree push --prefix=repo_$repo target main
    cd ..

    # Remove the cloned repo to clean up
    rm -rf $repo
done

echo "Consolidation completed successfully!"