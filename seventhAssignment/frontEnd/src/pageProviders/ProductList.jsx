import React from 'react';
import PageAccessValidator from 'components/PageAccessValidator';
import ProductListPage from 'pages/productList';
import PageContainer from 'components/PageContainer';

const ProductList = () => (
    <PageAccessValidator>
        <PageContainer>
            <ProductListPage/>
        </PageContainer>
    </PageAccessValidator>
);

export default ProductList;
