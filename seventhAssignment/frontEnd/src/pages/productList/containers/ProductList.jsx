import {postJson} from "../../../requests";
import {useState} from "react";
import Button from 'components/Button';
import Box from 'components/Box';
import Grid from 'components/Grid';

const ProductList = ({authorities}) => {
    const [productList, setProductList] = useState([]);
    const getProductList = () => {
        postJson({
            body: {
                productName: "product",
                page: 0,
                pageSize: 10
            },
            url: "http://localhost:8081/product/_filter"
        }).then(value => setProductList(value.content))
    }
    getProductList();

    return (
        <div>
            <Button variant="contained" color="primary" href="/createEditProduct">
                Create
            </Button>
            <div> {
                productList.map(value =>
                    (
                        <Grid>
                            <Box sx={{m: 2}}/>
                            {value.name}

                            <Button variant="contained" color="primary" href={"/createEditProduct/" + value.id}>
                                Edit
                            </Button>
                            <Button variant="contained" color="secondary">
                                Delete
                            </Button>

                        </Grid>

                    )
                )
            } </div>
        </div>
    )

}
export default ProductList