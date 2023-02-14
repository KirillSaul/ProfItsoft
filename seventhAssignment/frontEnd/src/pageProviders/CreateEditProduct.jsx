import React from 'react';
import PageAccessValidator from 'components/PageAccessValidator';
import CreateEditProductPage from 'pages/createEditProduct';
import PageContainer from 'components/PageContainer';

const CreateEditProduct = () => (
    <PageAccessValidator>
        <PageContainer>
            <CreateEditProductPage/>
        </PageContainer>
    </PageAccessValidator>
);

export default CreateEditProduct;